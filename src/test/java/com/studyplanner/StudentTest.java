package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.model.Program;
import com.studyplanner.model.Student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    @Test
    void shouldCalculateCompletedCredits(){
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(1L, "Maxime", program);
        Course course = new Course("DA123A", "Java Programming", 7.5);

        course.complete();
        student.addCourse(course);
        assertEquals(7.5, student.getCompletedCredits());
    }
}
