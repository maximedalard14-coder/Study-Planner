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

public class CourseFileRepositoryTest {

    private final String TEST_FILE = "testCourses.txt";
    private final CourseFileRepository courseFileRepository = new CourseFileRepository();

    @AfterEach
    void cleanUp(){
        new File(TEST_FILE).delete();
    }

    @Test
    void showLoadFromFile() throws IOException {
        List<Course> courses = new ArrayList<>();
        Course course = new Course("DA123A", "Java Programming", 7.5);
        course.complete();
        courses.add(course);

        courseFileRepository.saveCourses(courses, TEST_FILE);
        List<Course> loadedCourses = courseFileRepository.loadCourses(TEST_FILE);
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

        courseFileRepository.saveCourses( courses , TEST_FILE);
        List<Course> loadedCourses = courseFileRepository.loadCourses(TEST_FILE);

        assertEquals(3 , loadedCourses.size());
    }
}
