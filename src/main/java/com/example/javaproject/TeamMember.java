package com.example.javaproject;

import java.util.Comparator;

public class TeamMember implements Comparable<TeamMember>{
    private int memberCode;
    private String memberName;
    private String memberMail;

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        if (memberName.equals(memberName.trim())) {
            this.memberName = memberName;
        }
        System.out.println("enter name without spaces");

    }

    public String getMemberMail() {
        return memberMail;
    }

    public void setMemberMail(String memberMail) {
        char r = memberMail.charAt(0);
        if (!memberMail.endsWith("@") && r != '@' && memberMail.indexOf("@") != -1) {
            this.memberMail = memberMail;
        } else
            System.out.println("mail address is not valid");

    }
    public TeamMember(){}
    public TeamMember(int memberCode, String memberName, String memberMail) {
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.memberMail = memberMail;
    }

    @Override
    public String toString() {
        return
                "memberCode=" + memberCode +
                ", memberName='" + memberName + '\'' +
                ", memberMail='" + memberMail + '\''
                ;
    }

    @Override
    public int compareTo(TeamMember o) {
        return  getMemberName().compareTo(o.getMemberName());
    }
}
