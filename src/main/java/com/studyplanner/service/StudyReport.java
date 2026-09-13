package com.studyplanner.service;

import com.studyplanner.model.Student;
import com.studyplanner.model.Course;

public class StudyReport {
    private final Student student;

    public StudyReport(Student student){
        this.student = student;
    }

    public String generate(){
        StringBuilder report =
                new StringBuilder();
        StudyStatistics statistics = new StudyStatistics(student);

        report.append("\n===== STUDY REPORT =====");

        report.append("\nStudent: ").append(student.getUserName());
        report.append("\nProgram: ").append(student.getProgram().getName());

        report.append("\nCompleted credits: ").append(student.getCompletedCredits()).append(" hp");
        report.append("\nDegree progress: ").append(String.format("%.2f", student.getDegreeProgress())).append("%");

        report.append("\n----- Statistics -----");
        report.append("\nTotal courses: ").append(statistics.getTotalCourses());
        report.append("\nCompleted courses: ").append(statistics.getCompletedCourses());
        report.append("\nRemaining courses: ").append(statistics.getRemainingCourses());
        report.append("\n");
        report.append("\n----- Course Overview -----");
        for(Course course: student.getCourses()){
            report.append("\n").append(course);
        }
        return report.toString();
    }
}
