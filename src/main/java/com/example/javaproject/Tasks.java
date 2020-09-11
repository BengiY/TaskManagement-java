package com.example.javaproject;

import javax.xml.crypto.Data;
import java.util.Date;
enum status {
    New,InProgress, Done
}
public class Tasks {
    private int taskCode;
    private String taskTitle;
    private String taskDescription;
    private TeamMember belongTo;
    private int taskEstimatedTime;
    private Date startDate;
    private Date endDate;
    private status taskStatus;
    private Long rating;
    public Tasks(){}

    public Tasks(int taskCode, String taskTitle, String taskDescription, TeamMember belongTo, int taskEstimatedTime, Date startDate, Date endDate, status taskStatus, Long rating) {
        this.taskCode = taskCode;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.belongTo = belongTo;
        this.taskEstimatedTime = taskEstimatedTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskStatus = taskStatus;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "taskCode=" + taskCode +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", belongTo=" + belongTo +
                ", taskEstimatedTime=" + taskEstimatedTime +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", taskStatus=" + taskStatus +
                ", rating=" + rating +
                '}';
    }

    public int getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TeamMember getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(TeamMember belongTo) {
        this.belongTo = belongTo;
    }

    public int getTaskEstimatedTime() {
        return taskEstimatedTime;
    }

    public void setTaskEstimatedTime(int taskEstimatedTime) {
        this.taskEstimatedTime = taskEstimatedTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
