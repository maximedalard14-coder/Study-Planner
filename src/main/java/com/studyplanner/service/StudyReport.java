package com.studyplanner.service;

import com.studyplanner.model.Student;
import com.studyplanner.model.Course;

public class StudyReport {
    private final Student student;

    public StudyReport(Student student){
        this.student = student;
    }

    public void generate(){
        StudyStatistics statistics = new StudyStatistics(student);

        System.out.println("\n===== STUDY REPORT =====");

        System.out.println("Student: " + student.getUserName());
        System.out.println("Program: " + student.getProgram().getName());
        System.out.println("Completed credits: " + student.getCompletedCredits() + " hp" );
        System.out.println("Degree progress: " + String.format("%.2f", student.getDegreeProgress()) + "%");

        System.out.println("\n----- Statistics -----");
        System.out.println("Total courses: " +statistics.getTotalCourses());
        System.out.println("Completed courses: " + statistics.getCompletedCourses());
        System.out.println("Remaining courses: "+ statistics.getRemainingCourses());
        System.out.println();
        System.out.println("\n----- Course Overview -----");
        for(Course course: student.getCourses()){
            System.out.println(course);
        }
    }
}
