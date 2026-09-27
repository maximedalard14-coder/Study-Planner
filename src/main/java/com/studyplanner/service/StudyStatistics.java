package com.studyplanner.service;

import com.studyplanner.model.Student;
import com.studyplanner.model.Enrollment;

public class StudyStatistics {
    private final Student student;

    public StudyStatistics(Student student) {
        this.student = student;
    }

    public int getTotalCourses() {
        return student.getEnrollments().size();
    }

    public int getCompletedCourses() {
        int count = 0;
        for (Enrollment enrollment : student.getEnrollments()) {
            if (enrollment.isCompleted()) {
                count++;
            }
        }
        return count;
    }

    public int getRemainingCourses() {
        return getTotalCourses() - getCompletedCourses();
    }

}
