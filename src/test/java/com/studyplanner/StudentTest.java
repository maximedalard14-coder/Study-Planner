package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.model.Program;
import com.studyplanner.model.Student;
import com.studyplanner.model.Enrollment;
import com.studyplanner.model.Semester;

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

class StudentTest {


    CourseFileRepository repository = new CourseFileRepository();


    @AfterEach
    void cleanUp() {
        new File("testCourses.txt").delete();
    }

    @Test
    void shouldCalculateCompletedCredits() {
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(1L, "Maxime", program);
        Semester semester = new Semester("HT26");
        Course course = new Course(1L, "DA123A", "Java Programming", 7.5);

        Enrollment enrollment = new Enrollment(student, course, semester);
        enrollment.complete("A");
        student.addEnrollment(enrollment);

        assertEquals(7.5, student.getCompletedCredits(), 0.001);
    }

    @Test
    void shouldCalculateDegreeProgress() {
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(1L, "Maxime", program);
        Semester semester = new Semester("HT26");
        Course course = new Course(1L, "DA123A", "Java Programming", 7.5);

        Enrollment enrollment = new Enrollment(student, course, semester);
        enrollment.complete("A");
        student.addEnrollment(enrollment);

        assertEquals(4.1667, student.getDegreeProgress(), 0.001);
    }

    @Test
    void shouldReturnZeroProgressWhenProgramRequiresZeroCredits() {
        Program testProgram = new Program("Test Program", 0);
        Student testStudent = new Student(1L, "Maxime", testProgram);

        assertEquals(0, testStudent.getDegreeProgress());
    }

    @Test
    void shouldCalculateCompletedCourses() {
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(1L, "Maxime", program);
        Semester semester = new Semester("HT26");
        Course course = new Course(1L, "DA123A", "Java Programming", 7.5);

        Enrollment enrollment = new Enrollment(student, course, semester);
        enrollment.complete("A");
        student.addEnrollment(enrollment);

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
