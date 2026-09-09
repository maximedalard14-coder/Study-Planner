package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.model.Program;
import com.studyplanner.model.Student;

import com.studyplanner.repository.CourseFileRepository;
import com.studyplanner.service.StudyStatistics;
import org.junit.jupiter.api.Test;

import java.io.File;

import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest {


    CourseFileRepository repository = new CourseFileRepository();


    @AfterEach
    void cleanUp() {
        new File("testCourses.txt").delete();
    }

    @Test
    void shouldCalculateCompletedCredits() {
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(1L, "Maxime", program);
        Course course = new Course("DA123A", "Java Programming", 7.5);

        course.complete();
        student.addCourse(course);
        assertEquals(7.5, student.getCompletedCredits());
    }

    @Test
    void shouldCalculateDegreeProgress() {
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(1L, "Maxime", program);

        Course course = new Course("DA123A", "Java Programming", 7.5);
        course.complete();
        student.addCourse(course);
        assertEquals(4.166666666666666, student.getDegreeProgress());


    }

    @Test
    void shouldReturnZeroProgressWhenProgramRequiresZeroCredits() {
        Course course = new Course("DA123A", "Java Programming", 7.5);
        Program testProgram = new Program("Test Program", 0);
        Student testStudent = new Student(1L, "Maxime", testProgram);

        assertEquals(0, testStudent.getDegreeProgress());
    }

    @Test
    void shouldCalculateCompletedCourses() {
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(1L, "Maxime", program);
        Course course = new Course("DA123A", "Java Programming", 7.5);
        course.complete();
        student.addCourse(course);
        StudyStatistics statistics = new StudyStatistics(student);
        assertEquals(1, statistics.getCompletedCourses());


    }

    @Test
    void shouldLoadCourseFromFile() throws IOException {
        List<Course> courses = new ArrayList<>();
        Course course = new Course("DA123A", "Java Programming", 7.5);
        course.complete();
        courses.add(course);

        repository.saveCourses(courses, "testCourses.txt");
        List<Course> loadedCourses = repository.loadCourses("testCourses.txt");
        assertEquals(1, loadedCourses.size());
        assertEquals("DA123A", loadedCourses.get(0).getCourseCode());
        assertEquals("Java Programming", loadedCourses.get(0).getName());
        assertEquals(7.5, loadedCourses.get(0).getCredits());
        assertTrue(loadedCourses.get(0).isCompleted());


    }
}
