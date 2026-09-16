package com.studyplanner.repository;

import com.studyplanner.model.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseFileRepository {

    public void saveCourses(List<Course> courses, String fileName)
            throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for(Course course : courses){
            writer.write(course.getCourseCode() +"," +course.getName()
                    +"," + course.getCredits() + "," + course.isCompleted()
                    + "\n");
        }
        writer.close();
    }
    public List<Course> loadCourses(String fileName) throws IOException {

        List<Course> courses = new ArrayList<>();
        BufferedReader reader = new BufferedReader( new FileReader(fileName));

        String line;

        while((line = reader.readLine()) != null){
            String[] parts = line.split(",");
            String courseCode = parts[0];
            String courseName = parts[1];
            double credits = Double.parseDouble(parts[2]);
            boolean completed = Boolean.parseBoolean(parts[3]);
            Course course = new Course(courseCode, courseName , credits);
            if(completed){
                course.complete();
            }
            courses.add(course);


        }
        reader.close();

        return  courses;

    }
}
