package com.studyplanner;

import com.studyplanner.model.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class EnrollmentTest {
    @Test
    void shouldStoreSemesterOnEnrollment(){
        Program program = new Program("Test" , 180);
        Student owner = new Student(1L, "owner", program);
        Student other = new Student(2L, "other", program);
        Semester semester = new Semester("HT26");
        Course course = new Course("DA123A", "Java", 7.5);

        Enrollment enrollment = new Enrollment (other , course , semester);
        owner.addEnrollment(enrollment);

        assertSame(owner , enrollment.getStudent());
        assertEquals(1 , owner.getEnrollments().size());
        assertSame(enrollment, owner.getEnrollments().get(0));
    }

    @Test
    void getEnrollmentsShouldReturnDefensiveCopy(){
        Program program = new Program("Test" , 180);
        Student student = new Student(1L, "test", program);

        student.getEnrollments().clear();
        assertEquals(0 , student.getEnrollments().size());
    }
}
