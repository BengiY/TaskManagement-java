package com.example.javaproject;

public class Implement extends TeamMember {
    private int rating;

    public Implement(int memberCode, String memberName, String memberMail, int rating) {
        super(memberCode, memberName, memberMail);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
