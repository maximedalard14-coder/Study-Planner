package com.studyplanner.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final Long id;
    private final String userName;
    private final Program program;
    private final List<Course> courses;
    private final List<Semester> semesters;

    public Student(Long id, String userName, Program program) {
        this.id = id;
        this.userName = userName;
        this.program = program;
        this.courses = new ArrayList<>();
        this.semesters= new ArrayList<>();
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

    public double getCompletedCredits() {
        double credits = 0;

        for (Course course : courses) {
            if (course.isCompleted()) {
                credits += course.getCredits();
            }
        }
        return credits;
    }

    public double getDegreeProgress() {
        if (program.getRequiredCredits() == 0) {
            return 0.0;
        }
        return (getCompletedCredits()
                / program.getRequiredCredits())
                * 100;
    }
}