package com.studyplanner.model;

public class Course {
    private Long id;
    private String courseCode;
    private String name;
    private double credits;

    public Course(Long id, String courseCode, String name, double credits) {
        this.id = id;
        this.courseCode = courseCode;
        this.name = name;
        this.credits = credits;
    }
    @Deprecated
    public Course(String courseCode, String name, double credits) {
        this(null, courseCode, name, credits);
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
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
                " hp)";
    }
}