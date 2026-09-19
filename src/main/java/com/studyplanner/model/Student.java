package com.studyplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private  Long id;
    private  String userName;
    private  Program program;
    private  List<Course> courses;
    private  List<Semester> semesters;

    public Student(Long id, String userName, Program program) {
        this.id = id;
        this.userName = userName;
        this.program = program;
        this.courses = new ArrayList<>();
        this.semesters= new ArrayList<>();
    }

    public Student(){}

    public void setId(Long id){
        this.id =  id;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setProgram(Program program) {
        this.program = program;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
    public void addSemester(Semester semester){
        semesters.add(semester);
    }
    public List<Semester> getSemesters(){
        return new ArrayList<>(semesters);
    }

    public Long getId() {
        return id;
    }

    public Program getProgram() {
        return program;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public String getUserName() {
        return userName;
    }

    @JsonIgnore
    public double getCompletedCredits() {
        double credits = 0;

        for (Course course : courses) {
            if (course.isCompleted()) {
                credits += course.getCredits();
            }
        }
        return credits;
    }
    @JsonIgnore
    public double getDegreeProgress() {
        if (program.getRequiredCredits() == 0) {
            return 0.0;
        }
        return (getCompletedCredits()
                / program.getRequiredCredits())
                * 100;
    }

    @Override
    public String toString() {

        return "Student{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", program=" + program +
                ", courses=" + courses.size() +
                ", semesters=" + semesters.size() +
                '}';
    }


}