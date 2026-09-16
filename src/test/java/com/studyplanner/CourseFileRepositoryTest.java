package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.repository.CourseFileRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseFileRepositoryTest {

    private final String testfile = "testCourses.txt";
    private final CourseFileRepository courseFileRepository = new CourseFileRepository();

    @AfterEach
    void cleanUp(){
        new File(testfile).delete();
    }

    @Test
    void showLoadFromFile() throws IOException {
        List<Course> courses = new ArrayList<>();
        Course course = new Course("DA123A", "Java Programming", 7.5);
        course.complete();
        courses.add(course);

        courseFileRepository.saveCourses(courses, testfile);
        List<Course> loadedCourses = courseFileRepository.loadCourses(testfile);
        assertEquals(1, loadedCourses.size());
        assertEquals("DA123A", loadedCourses.get(0).getCourseCode());
        assertEquals("Java Programming", loadedCourses.get(0).getName());
        assertEquals(7.5, loadedCourses.get(0).getCredits());
        assertTrue(loadedCourses.get(0).isCompleted());
    }

    @Test
    void shouldLoadMultipleCourses() throws IOException{
        List<Course> courses = new ArrayList<>();
        Course javaCourse = new Course("DA123A", "Java Programming", 7.5);
        Course databaseCourse = new Course("DA234B", "Databases" , 7.5);
        Course algorithmsCourse = new Course("DA345C", "Algorithms", 7.5);

        courses.add(javaCourse);
        courses.add(databaseCourse);
        courses.add(algorithmsCourse);

        courseFileRepository.saveCourses( courses , testfile);
        List<Course> loadedCourses = courseFileRepository.loadCourses(testfile);

        assertEquals(3 , loadedCourses.size());
    }
}
