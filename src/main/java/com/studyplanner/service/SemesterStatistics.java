package com.studyplanner.service;

import com.studyplanner.model.Enrollment;
import com.studyplanner.model.Semester;
import com.studyplanner.model.Student;

import java.util.ArrayList;
import java.util.List;

public class SemesterStatistics {

    private final Student student;
    private final Semester semester;

    public SemesterStatistics(Student student, Semester semester) {
        this.student = student;
        this.semester = semester;
    }

    private List<Enrollment> enrollmentsInSemester() {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : student.getEnrollments()) {
            if (semester.equals(enrollment.getSemester())) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public int getTotalCourses() {
        return enrollmentsInSemester().size();
    }

    public double getTotalCredits() {
        double total = 0;
        for (Enrollment enrollment : enrollmentsInSemester()) {
            if (enrollment.getCourse() != null) {
                total += enrollment.getCourse().getCredits();
            }
        }
        return total;
    }

    public double getCompletedCredits() {
        double completed = 0;
        for (Enrollment enrollment : enrollmentsInSemester()) {
            if (enrollment.isCompleted() && enrollment.getCourse() != null) {
                completed += enrollment.getCourse().getCredits();
            }
        }
        return completed;
    }

    public int getCompletedCourses() {
        int count = 0;
        for (Enrollment enrollment : enrollmentsInSemester()) {
            if (enrollment.isCompleted()) {
                count++;
            }
        }
        return count;
    }
}


