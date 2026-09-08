package com.studyplanner.service;

import com.studyplanner.model.Student;
import com.studyplanner.model.Course;

public class StudyStatistics {
    private final Student student;
    public StudyStatistics(Student student) {
        this.student = student;
    }
    public int getTotalCourses(){
        return student.getCourses().size();
    }
    public int getCompletedCourses(){
        int count=0;
        for(Course course: student.getCourses()){
            if(course.isCompleted()){
                count++;
            }
        }
        return count;
    }
    public int getRemainingCourses(){
        return getTotalCourses() - getCompletedCourses();
    }

}
