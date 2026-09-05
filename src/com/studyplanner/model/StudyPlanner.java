package com.studyplanner.model;

import java.util.ArrayList;
import java.util.List;

public class StudyPlanner {

    private final ArrayList<Course> courses;

    public StudyPlanner() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean completeCourse(String courseCode) {

        for (Course course : courses) {

            if (course.getCourseCode()
                    .equalsIgnoreCase(courseCode)) {

                course.complete();
                return true;
            }
        }

        return false;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public int getNumberOfCourses() {
        return courses.size();
    }

    public double calculateTotalCredits() {

        double totalCredits = 0;

        for (Course course : courses) {
            totalCredits += course.getCredits();
        }

        return totalCredits;
    }

    public double calculateCompletedCredits() {

        double completedCredits = 0;

        for (Course course : courses) {

            if (course.isCompleted()) {
                completedCredits += course.getCredits();
            }
        }

        return completedCredits;
    }

    public double getCompletionPercentage() {

        double totalCredits = calculateTotalCredits();

        if (totalCredits == 0) {
            return 0.0;
        }

        return (calculateCompletedCredits() / totalCredits) * 100;
    }
}
