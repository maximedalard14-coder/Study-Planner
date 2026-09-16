package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.model.Program;
import com.studyplanner.model.Student;
import com.studyplanner.service.StudyReport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StudyReportTest {

    @Test
    void shouldGenerateReportContainingStudentInformation() {
        Program program = new Program("Data och Systemvetenskap", 180.0);
        Student student = new Student(1L, "mada4843", program);
        Course course = new Course("DA123A", "Java Programming", 7.5);
        course.complete();
        student.addCourse(course);
        StudyReport studyReport = new StudyReport(student);
        String studyReportText = studyReport.generate();

        assertTrue(studyReportText.contains("mada4843"));
        assertTrue(studyReportText.contains("Data och Systemvetenskap"));
        assertTrue(studyReportText.contains("Java Programming"));
        assertTrue(studyReportText.contains("Total courses: 1"));
        assertTrue(studyReportText.contains("Completed courses: 1"));
        assertTrue(studyReportText.contains("Remaining courses: 0"));


    }
}
