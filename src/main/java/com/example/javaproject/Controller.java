package com.example.javaproject;

import javafx.print.Collation;
import org.springframework.core.annotation.SynthesizedAnnotation;
import sun.font.CreatedFontTracker;

import java.util.*;

public class Controller {

    public static  DBAccess myDb = new DBAccess();

    public static void invokeThreads(int taskCode, status taskStatus) {
        UpdateStatusThread updateStatusThread = new UpdateStatusThread(taskCode, taskStatus);
        CheckStatusThread checkStatusThread = new CheckStatusThread(taskCode, taskStatus);
        updateStatusThread.start();
        checkStatusThread.start();
    }

    public static String existTeam(String name, String mail) throws ClassNotFoundException {
        List<TeamMember> TeamList = new ArrayList<TeamMember>();
        TeamList=myDb.getAllTeamMember();
        Map<String, String> TeamMap = new HashMap<String, String>();
        for (TeamMember t : TeamList) {
            TeamMap.put(t.getMemberName(), t.getMemberMail());
        }
       String getMail= TeamMap.get(name);
        if(getMail.equals(mail)) {
            return "exist team member";
        }
        else
            return "not exist team member";
    }

    public static List<Tasks> getallTaskByMemberCode(int TeamCode) throws ClassNotFoundException {
         return myDb.getAllTaskByMemberCode(TeamCode);
    }

    public static List<TeamMember> getallTeamMember() throws ClassNotFoundException {
        List<TeamMember> TeamList = new ArrayList<TeamMember>();
                TeamList= myDb.getAllTeamMember();
                Collections.sort(TeamList,TeamMember::compareTo);
                return TeamList;

    }

    public static void intilazingLists() throws ClassNotFoundException {
        myDb.initializing();
    }
}

