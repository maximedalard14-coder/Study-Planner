package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.model.Enrollment;
import com.studyplanner.model.Program;
import com.studyplanner.model.Semester;
import com.studyplanner.model.Student;
import com.studyplanner.service.SemesterStatistics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SemesterStatisticsTest {

    private Student buildStudent() {
        Program program = new Program("Test", 180);
        return new Student(1L, "test", program);
    }

    @Test
    void shouldCalculateCompletedCredits() {
        Student student = buildStudent();
        Semester semester = new Semester("HT26");

        Course java = new Course(1L, "DA123A", "Java Programming", 7.5);
        Course db = new Course(2L, "DA234B", "Databases", 7.5);
        Course algo = new Course(3L, "DA234C", "Algorithms", 7.5);

        Enrollment e1 = new Enrollment(student, java, semester);
        Enrollment e2 = new Enrollment(student, db, semester);
        Enrollment e3 = new Enrollment(student, algo, semester);
        e1.complete("A");
        e2.complete("B");
        e3.complete("A");

        student.addEnrollment(e1);
        student.addEnrollment(e2);
        student.addEnrollment(e3);

        SemesterStatistics statistics = new SemesterStatistics(student, semester);
        assertEquals(22.5, statistics.getCompletedCredits(), 0.001);
    }

    @Test
    void shouldCalculateTotalCredits() {
        Student student = buildStudent();
        Semester semester = new Semester("HT26");

        Course logic = new Course(4L, "DA123D", "Logic", 7.5);
        Course java = new Course(1L, "DA123A", "Java Programming", 7.5);
        Course db = new Course(2L, "DA234B", "Databases", 7.5);
        Course algo = new Course(3L, "DA234C", "Algorithms", 7.5);

        student.addEnrollment(new Enrollment(student, logic, semester));
        student.addEnrollment(new Enrollment(student, java, semester));
        student.addEnrollment(new Enrollment(student, db, semester));
        student.addEnrollment(new Enrollment(student, algo, semester));

        SemesterStatistics statistics = new SemesterStatistics(student, semester);
        assertEquals(30.0, statistics.getTotalCredits(), 0.001);
    }

    @Test
    void shouldOnlyCountEnrollmentsInTheGivenSemester() {
        Student student = buildStudent();
        Semester ht26 = new Semester("HT26");
        Semester vt27 = new Semester("VT27");

        Course java = new Course(1L, "DA123A", "Java Programming", 7.5);
        Course db = new Course(2L, "DA234B", "Databases", 7.5);

        student.addEnrollment(new Enrollment(student, java, ht26));
        student.addEnrollment(new Enrollment(student, db, vt27));

        SemesterStatistics ht26Stats = new SemesterStatistics(student, ht26);
        SemesterStatistics vt27Stats = new SemesterStatistics(student, vt27);

        assertEquals(1, ht26Stats.getTotalCourses());
        assertEquals(1, vt27Stats.getTotalCourses());
        assertEquals(7.5, ht26Stats.getTotalCredits(), 0.001);
        assertEquals(7.5, vt27Stats.getTotalCredits(), 0.001);
    }
}