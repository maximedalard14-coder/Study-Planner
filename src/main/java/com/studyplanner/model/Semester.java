package com.studyplanner.model;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    private  String name;
    private  List<Course> courses;

    public Semester(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }
    public Semester(){}

    public void setName(String name){
        this.name = name;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public String getName() {
        return name;
    }
}
