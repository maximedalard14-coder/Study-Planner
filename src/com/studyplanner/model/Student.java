package com.studyplanner.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final Long id;
    private final String userName;
    private final List<Course> courses;

    public Student(Long id, String userName) {
        this.id = id;
        this.userName = userName;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public Long getId() {
        return id;
    }
    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public String getUserName() {
        return userName;
    }

    public double getCompletedCredits(){
        double credits = 0;

        for(Course course : courses){
            if(course.isCompleted()) {
                credits += course.getCredits();
            }
        }
        return credits;
    }
}