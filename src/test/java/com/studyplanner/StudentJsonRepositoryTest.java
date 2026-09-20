package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.model.Student;
import com.studyplanner.model.Semester;
import com.studyplanner.model.Program;
import com.studyplanner.repository.StudentJsonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentJsonRepositoryTest {

    @TempDir
    Path tempDir;
    @Test
    void shouldSaveAndLoadCompleteStudentAsJson() throws IOException {

        Program program = new Program("Data och Systemvetenskap" , 180);
        Student student = new Student(20060625L, "mada4843" , program);
        Course javaCourse = new Course("DA123A", "Java Programming", 7.5);
        Course dbCourse = new Course("DA234B", "Databases", 7.5);

        javaCourse.complete();
        dbCourse.complete();

        Semester semester = new Semester("HT2026");
        semester.addCourse(javaCourse);
        semester.addCourse(dbCourse);
        student.addCourse(javaCourse);
        student.addCourse(dbCourse);
        student.addSemester(semester);

        Path file = tempDir.resolve("student-roundtrip.json");
        StudentJsonRepository repository = new StudentJsonRepository();

        repository.saveStudent(student, file);
        Student loadedStudent = repository.loadStudent(file);

        //assert identitet
        assertEquals(student.getId() , loadedStudent.getId());
        assertEquals(student.getUserName() , loadedStudent.getUserName());
        assertEquals(student.getProgram(), loadedStudent.getProgram());

        //assert kurser
        assertEquals(2 , loadedStudent.getCourses().size());
        Course loadedJava = loadedStudent.getCourses().get(0);
        assertEquals("DA123A" , loadedJava.getCourseCode());
        assertEquals("Java Programming" , loadedJava.getName());
        assertEquals(7.5, loadedJava.getCredits() , 0.01);
        assertTrue(loadedJava.isCompleted() , "Course.completed borde ha bevarats genom JSON round-trip");

        //assert terminer
        assertEquals(1 , loadedStudent.getSemesters().size());
        Semester loadedSemester = loadedStudent.getSemesters().get(0);
        assertEquals("HT2026" , loadedSemester.getName());
        assertEquals(2 , loadedSemester.getCourses().size() ,  "Semester.courses borde ha bevarats genom JSON round-trip");


    }

    @Test
    void findStudentShouldReturnEmptyWhenFileDOesNotExist() throws IOException {

        Path missing = tempDir.resolve("does-not-exist.json");
        StudentJsonRepository repository = new StudentJsonRepository();

        Optional<Student> result = repository.findStudent(missing);

        assertTrue(result.isEmpty());
    }

    @Test
    void loadStudentShouldIgnoreUnknownJsonFields() throws IOException{
        Path file = tempDir.resolve("student-with-extra-fields.json");
        Files.writeString(file , """ 
              {
              "id": 1,
              "userName": "mada4843",
              "program": { "name": "Data och Systemvetenskap", "requiredCredits": 180.0 },
              "courses": [],
              "semesters": [],
              "futureFieldWeDontKnowYet": "ignored"
            }
            """);

        StudentJsonRepository repository = new StudentJsonRepository();
        Student loadedStudent = repository.loadStudent(file);

        assertEquals("mada4843" , loadedStudent.getUserName());

    }

    @Test
    void loadStudentShouldThrowOnInvalidJson() throws IOException{
        Path file = tempDir.resolve("broken.json");
        Files.writeString(file , "{this is not json}");
        StudentJsonRepository repository = new StudentJsonRepository();

        assertThrows(IOException.class, () -> repository.loadStudent(file));

    }
}
