package com.studyplanner.model;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    private final String name;
    private final List<Course> courses;

    public Semester(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
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
