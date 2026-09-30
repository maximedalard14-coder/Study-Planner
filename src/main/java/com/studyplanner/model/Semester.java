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

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Semester semester = (Semester) o;
        return name != null && name.equals(semester.name);
    }

    public int hashCode(){
        return name != null ? name.hashCode() : 0;
    }
}
