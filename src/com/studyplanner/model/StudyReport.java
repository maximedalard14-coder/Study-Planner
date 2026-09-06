package com.studyplanner.model;

public class StudyReport {
    private final Student student;

    public StudyReport(Student student){
        this.student = student;
    }

    public void generate(){

        System.out.println("\n===== STUDY REPORT =====");

        System.out.println("Student: " + student.getUserName());
        System.out.println("Program: " + student.getProgram().getName());
        System.out.println("Completed credits: " + student.getCompletedCredits() + " hp" );
        System.out.println("Degree progress: " + String.format("%.2f", student.getDegreeProgress()) + "%");
    }
}
