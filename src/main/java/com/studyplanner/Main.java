package com.studyplanner;

import com.studyplanner.model.*;
import com.studyplanner.repository.StudentJsonRepository;
import com.studyplanner.service.SemesterStatistics;
import com.studyplanner.service.StudyReport;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        Program sysdk = new Program("Data och Systemvetenskap", 180);

        Student student = new Student(20060625L, "mada4843", sysdk);

        Semester ht26 = new Semester("HT26");

        Course javaCourse = new Course(1L, "DA123A", "Java Programming", 7.5);
        Course databaseCourse = new Course(2L, "DA234B", "Databases", 7.5);
        Course algorithmsCourse = new Course(3L, "DA234C", "Algorithms", 7.5);

        Enrollment javaEnrollment = new Enrollment(student, javaCourse, ht26);
        Enrollment dbEnrollment = new Enrollment(student, databaseCourse, ht26);
        Enrollment algoEnrollment = new Enrollment(student, algorithmsCourse, ht26);

        javaEnrollment.complete("A");
        dbEnrollment.complete("B");
        algoEnrollment.complete("A");

        student.addEnrollment(javaEnrollment);
        student.addEnrollment(dbEnrollment);
        student.addEnrollment(algoEnrollment);

        ht26.addCourse(javaCourse);
        ht26.addCourse(databaseCourse);
        ht26.addCourse(algorithmsCourse);
        student.addSemester(ht26);

        StudyReport report = new StudyReport(student);
        System.out.println(report.generate());

        SemesterStatistics statistics = new SemesterStatistics(ht26);
        System.out.println("Semester: " + ht26.getName());
        System.out.println("Courses: " + statistics.getTotalCourses());
        System.out.println("Credits: " + statistics.getTotalCredits());

        StudentJsonRepository repository = new StudentJsonRepository();
        Path file = Path.of("student.json");
        repository.saveStudent(student, file);
        Student loaded = repository.loadStudent(file);
        System.out.println(loaded);
    }
}