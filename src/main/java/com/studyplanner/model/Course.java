package com.studyplanner.model;

public class Course {
    private  String courseCode;
    private String name;
    private boolean completed;
    private double credits;

    public Course(String courseCode, String name, double credits) {
        this.courseCode = courseCode;
        this.name = name;
        this.credits = credits;
        this.completed = false;
    }
    public Course(){}

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void complete() {
        this.completed = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public double getCredits() {
        return this.credits;
    }

    @Override
    public String toString() {
        return courseCode +
                " - " +
                name +
                " (" +
                credits +
                " hp) - " +
                (completed ? "Completed" : "Not completed");
    }
}
