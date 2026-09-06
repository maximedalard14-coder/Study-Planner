package com.studyplanner.model;

public class Enrollment {

    private final Student student;
    private final Course course;

    private boolean completed;
    private String grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.completed = false;
    }

    public void complete(String grade) {
        this.completed = true;
        this.grade = grade;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public Student getStudent() {
        return student;
    }

    public String getGrade() {
        return grade;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return student.getUserName()
                + " enrolled in " + course.getName() +
                " (" + (completed ? "Completed " : "Ongoing") + ")";
    }
}
