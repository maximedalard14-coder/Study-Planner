package com.studyplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Enrollment {

    private Student student;
    private Course course;
    private Semester semester;

    private boolean completed;
    private String grade;

    public Enrollment(Student student, Course course, Semester semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.completed = false;
    }

    @Deprecated
    public Enrollment(Student student, Course course) {
        this(student, course, null);
    }

    public Enrollment() {
    }

    public void complete(String grade) {
        this.completed = true;
        this.grade = grade;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @JsonBackReference
    public Student getStudent() {
        return student;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        String studentName = student != null ? student.getUserName() : "unknown";
        String courseName = course != null ? course.getName() : "unknown course";
        String semesterName = semester != null ? semester.getName() : "no semester";
        return studentName
                + " enrolled in " + courseName
                + "(" + semesterName + ")"
                + " (" + (completed ? "Completed " : "Ongoing") + ")";
    }
}
