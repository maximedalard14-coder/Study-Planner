package com.studyplanner.service;
import com.studyplanner.model.Course;
import com.studyplanner.model.Semester;

public class SemesterStatistics {

    private final Semester semester;

    public SemesterStatistics(Semester semester) {
        this.semester = semester;
    }

    public int getTotalCourses(){
        return semester.getCourses().size();
    }
    public double getTotalCredits(){
        double totalCredits = 0;

        for(Course course : semester.getCourses()){
            totalCredits += course.getCredits();
        }
        return totalCredits;
    }
    public double getCompletedCredits(){
        double completedCredits = 0;
        for(Course course : semester.getCourses()){
            if(course.isCompleted()){
                completedCredits += course.getCredits();
            }
        }
        return completedCredits;
    }
    public int getCompletedCourses(){
        int completedCourses = 0;
        for(Course course : semester.getCourses()){
            if(course.isCompleted()){
                completedCourses += 1;
            }
        }
        return completedCourses;
    }


}
