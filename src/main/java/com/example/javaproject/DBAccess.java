package com.example.javaproject;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBAccess {
    public static List<TeamMember> TeamList = new ArrayList<TeamMember>();
    public static List<Tasks> TasksList = new ArrayList<Tasks>();
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-2U2SABC;DatabaseName=JavaDB";
    static final String USER = "DESKTOP-2U2SABC\\YAELB";
    static final String PASS = "";


    public static List<Tasks> getAllTask() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement sta = conn.createStatement();
            String Sql = "select * from Tasks";
            ResultSet rs = sta.executeQuery(Sql);
            List<Tasks> t = new ArrayList<Tasks>();
            while (rs.next()) {
                String strSt = rs.getString("taskStatus");
                status s = status.valueOf(strSt);
                int mCode = rs.getInt("MemberCode");
                TeamMember x = new TeamMember();
                x.setMemberCode((Integer) mCode);
                t.add(new Tasks(rs.getInt("TaskCode"), rs.getString("TaskTitle"), rs.getString("TaskDescription")
                        , x, rs.getInt("TaskEstimatedTime"), rs.getDate("StartDate"),
                        rs.getDate("EndDate"), s, rs.getLong("rating")));
            }
            return t;

        } catch (Exception e) {
            System.out.println("cannot connect to sql");
            return TasksList;
        }

    }

    public static List<Tasks> getAllTaskByMemberCode(int MemberCode) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement sta = conn.createStatement();
            PreparedStatement statement = conn.prepareStatement("select * from Tasks where memberCode= ?");
            statement.setInt(1, MemberCode);
            ResultSet rs = statement.executeQuery();
            List<Tasks> t = new ArrayList<Tasks>();
            while (rs.next()) {
                int mCode = rs.getInt("MemberCode");
                String strSt = rs.getString("taskStatus");
                status s = status.valueOf(strSt);
                if (mCode == MemberCode) {
                    TeamMember x = new TeamMember();
                    x.setMemberCode((Integer) mCode);
                    t.add(new Tasks(rs.getInt("TaskCode"), rs.getString("TaskTitle"), rs.getString("TaskDescription")
                            , x, rs.getInt("TaskEstimatedTime"), rs.getDate("StartDate"),
                            rs.getDate("EndDate"), s, rs.getLong("rating")));
                }
            }
            return t;

        } catch (Exception e) {
            List<Tasks> tasksL = new ArrayList<Tasks>();
            System.out.println("cannot connect to sql");
            for (Tasks t : TasksList) {
                if (t.getBelongTo().getMemberCode() == MemberCode) {
                    tasksL.add(t);
                }
            }
            return tasksL;
        }

    }

    public static void addTask(Tasks taskToadd) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Tasks values(?,?,?,?,?,?,?,?) ");
            statement.setInt(1, taskToadd.getTaskCode());
            statement.setString(2, taskToadd.getTaskTitle());
            statement.setString(3, taskToadd.getTaskDescription());
            statement.setInt(4, taskToadd.getBelongTo().getMemberCode());
            statement.setInt(5, taskToadd.getTaskEstimatedTime());
            statement.setDate(6, (java.sql.Date) taskToadd.getStartDate());
            statement.setDate(7, (java.sql.Date) taskToadd.getEndDate());
            statement.setLong(8, taskToadd.getRating());
            TasksList.add(taskToadd);

        } catch (Exception e) {
            System.out.println("cannot connect to sql and insert");
            TasksList.add(taskToadd);
        }
    }

    public static Long updateStatus(int taskCode, status taskStatus) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Connecting to a selected database...");
        Date now = new Date();
        Date endDate = new Date();
        if (taskStatus == status.Done) {
            for (Tasks t : TasksList) {
                if (t.getTaskCode() == taskCode) {
                    endDate = t.getEndDate();
                    break;
                }
            }
            long diff = now.getTime() - endDate.getTime() / (1000 * 60 * 60 * 24);
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement statement = conn.prepareStatement("UPDATE Tasks SET taskStatus = ?,rating=? WHERE taskCode = ?");
                statement.setString(1, taskStatus.toString());
                statement.setLong(2, diff);
                statement.setInt(3, taskCode);
                return diff;

            } catch (Exception e) {
                System.out.println("cannot connect to sql");
                for (Tasks t : TasksList) {
                    if (t.getTaskCode() == taskCode) {
                        t.setRating(diff);
                        t.setTaskStatus(status.Done);
                        break;
                    }
                }
                return diff;
            }
        } else {
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement statement = conn.prepareStatement("UPDATE Tasks SET taskStatus = ? WHERE taskCode = ?");
                statement.setString(1, taskStatus.toString());
                statement.setInt(2, taskCode);
                return null;
            } catch (Exception e) {
                System.out.println("cannot connect to sql and update status");
                for (Tasks t : TasksList) {
                    if (t.getTaskCode() == taskCode) {
                        t.setTaskStatus(taskStatus);
                        break;
                    }
                }
                return null;
            }
        }
    }

    public static List<TeamMember> getAllTeamMember () throws ClassNotFoundException {
            Connection conn = null;
            Statement stmt = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Connecting to a selected database...");
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement sta = conn.createStatement();
                String Sql = "select * from TeamMember";
                ResultSet rs = sta.executeQuery(Sql);
                List<TeamMember> t = new ArrayList<TeamMember>();
                while (rs.next()) {
                    t.add(new TeamMember(rs.getInt("memberCode"), rs.getString("memberName"), rs.getString("memberMail")));
                }
                return t;

            } catch (Exception e) {
                System.out.println("cannot connect to sql");
                return TeamList;
            }

        }

    public static void initializing () throws ClassNotFoundException {
            TeamList.add(new TeamMember(111, "yosef", "sdssd@gmail.com"));
            TeamList.add(new TeamMember(222, "israel", "rerer@gmail.com"));
            TeamList.add(new TeamMember(333, "simon", "erer@gmail.com"));
            TeamList.add(new TeamMember(444, "izchak", "erere@gmail.com"));
            TasksList.add(new Tasks(1, "aaa", "descrip1", new TeamMember(111, "yosef", "sdssd@gmail.com"), 5, new Date(11 / 12 / 2020), new Date(11 / 12 / 2200), status.New, null));
            TasksList.add(new Tasks(2, "bbb", "descrip2", new TeamMember(222, "israel", "rerer@gmail.com"), 10, new Date(11 / 12 / 2020), new Date(11 / 12 / 2200), status.New, null));
            TasksList.add(new Tasks(3, "ccc", "descrip3", new TeamMember(333, "simon", "erer@gmail.com"), 25, new Date(11 / 12 / 2020), new Date(11 / 12 / 2200), status.New, null));
            TasksList.add(new Tasks(4, "ddd", "descrip4", new TeamMember(444, "izchak", "erere@gmail.com"), 60, new Date(11 / 12 / 2020), new Date(11 / 12 / 2200), status.New, null));
            TasksList.add(new Tasks(4, "eee", "descrip5", new TeamMember(444, "izchak", "erere@gmail.com"), 50, new Date(11 / 12 / 2020), new Date(11 / 12 / 2200), status.New, null));

            Connection conn = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Connecting to a selected database...");
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement sta = conn.createStatement();
                String Sql = "select * from Tasks";
                String Sql1 = "select * from TeamMember";
                ResultSet rs = sta.executeQuery(Sql);
                ResultSet rs1 = sta.executeQuery(Sql1);
                while (rs.next()) {
                    String strSt = rs.getString("taskStatus");
                    status s = status.valueOf(strSt);
                    int mCode = rs.getInt("MemberCode");
                    TeamMember x = new TeamMember();
                    x.setMemberCode((Integer) mCode);
                    TasksList.add(new Tasks(rs.getInt("TaskCode"), rs.getString("TaskTitle"), rs.getString("TaskDescription")
                            , x, rs.getInt("TaskEstimatedTime"), rs.getDate("StartDate"),
                            rs.getDate("EndDate"), s, rs.getLong("rating")));
                }
                while (rs1.next()) {
                    TeamList.add(new TeamMember(rs1.getInt("memberCode"), rs1.getString("memberName"), rs1.getString("memberMail")));
                }

            } catch (Exception e) {
                System.out.println("cannot connect to sql");
            }
        }
    }

