package com.studyplanner;

import com.studyplanner.model.*;
import com.studyplanner.service.SemesterStatistics;
import com.studyplanner.service.StudyReport;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {

        Program sysdk =
                new Program(
                        "Data och Systemvetenskap",
                        180);

        Student student =
                new Student(
                        20060625L,
                        "mada4843",
                        sysdk);

        Semester ht2026 =
                new Semester("HT2026");

        Course javaCourse =
                new Course(
                        "DA123A",
                        "Java Programming",
                        7.5);

        Course databaseCourse =
                new Course(
                        "DA234B",
                        "Databases",
                        7.5);

        Course algorithmsCourse =
                new Course(
                        "DA234C",
                        "Algorithms",
                        7.5);

        javaCourse.complete();
        databaseCourse.complete();
        algorithmsCourse.complete();

        ht2026.addCourse(javaCourse);
        ht2026.addCourse(databaseCourse);
        ht2026.addCourse(algorithmsCourse);

        student.addCourse(javaCourse);
        student.addCourse(databaseCourse);
        student.addCourse(algorithmsCourse);

        student.addSemester(ht2026);

        StudyReport report =
                new StudyReport(student);

        System.out.println(
                report.generate());

        SemesterStatistics statistics = new SemesterStatistics(ht2026);

        System.out.println("Semester: " + ht2026.getName());
        System.out.println("Courses: " + statistics.getTotalCourses());
        System.out.println("Credits: " + statistics.getTotalCredits());

    }
}