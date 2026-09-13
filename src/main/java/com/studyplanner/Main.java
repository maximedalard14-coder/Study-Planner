package com.studyplanner;
import com.studyplanner.model.*;
import com.studyplanner.repository.CourseFileRepository;
import com.studyplanner.repository.StudentFileRepository;
import com.studyplanner.service.*;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        Program sysdk =
                new Program(
                        "Data och Systemvetenskap",
                        180);

        Student student =
                new Student(
                        20060625L,
                        "mada4843",
                        sysdk);
        StudyReport report = new StudyReport(student);

        Course javaCourse =
                new Course(
                        "DA123A",
                        "Java Programming",
                        7.5);

        Enrollment enrollment =
                new Enrollment(
                        student,
                        javaCourse);

        enrollment.complete("A");

        Course databaseCourse= new Course("DA234B" , "Databases" , 7.5);
        Course algorithmsCourse = new Course("DA234C" , "Algorithms ", 7.5);
        javaCourse.complete();
        databaseCourse.complete();
        algorithmsCourse.complete();
        student.addCourse(javaCourse);
        student.addCourse(databaseCourse);
        student.addCourse(algorithmsCourse);


        System.out.println(enrollment);

        System.out.println(
                "Grade: "
                        + enrollment.getGrade());
        StudyStatistics statistics =
                new StudyStatistics(student);

        System.out.println(
                "Total Courses: "
                        + statistics.getTotalCourses());

        System.out.println(
                "Completed Courses: "
                        + statistics.getCompletedCourses());

        System.out.println(
                "Remaining Courses: "
                        + statistics.getRemainingCourses());


        CourseFileRepository repository = new CourseFileRepository();
        try {
            repository.saveCourses(student.getCourses(), "courses.txt");
            System.out.println("Courses saved successfully");

        }catch (Exception e) {
            System.out.println("Error saving courses " + e.getMessage());
            e.printStackTrace();
        }
        try {
            System.out.println("\nLoading courses...");
            List<Course> loadedCourses = repository.loadCourses("courses.txt");
            for (Course course : loadedCourses){
                System.out.println(course);
            }

        }catch (Exception e){
            System.out.println("Something went wrong " + e.getMessage());
            e.printStackTrace();
        }





        StudentFileRepository studentFileRepository = new StudentFileRepository();
        studentFileRepository.saveStudent(student, "student.txt");

        Student loadedStudent = studentFileRepository.loadStudent("student.txt");
        System.out.println("\nLoaded student: ");
        System.out.println(loadedStudent.getId());
        System.out.println(loadedStudent.getUserName());
        System.out.println(loadedStudent.getProgram());
        System.out.println(loadedStudent.getProgram().getRequiredCredits());




        System.out.println(
                report.generate());
    }



}