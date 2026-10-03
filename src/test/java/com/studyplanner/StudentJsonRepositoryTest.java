package com.studyplanner;

import com.studyplanner.model.Course;
import com.studyplanner.model.Enrollment;
import com.studyplanner.model.Program;
import com.studyplanner.model.Semester;
import com.studyplanner.model.Student;
import com.studyplanner.repository.StudentJsonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentJsonRepositoryTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldSaveAndLoadCompleteStudentAsJson() throws IOException {
        // Arrange
        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student(20060625L, "mada4843", program);

        Semester semester = new Semester("HT26");

        Course javaCourse = new Course(1L, "DA123A", "Java Programming", 7.5);
        Course dbCourse = new Course(2L, "DA234B", "Databases", 7.5);

        Enrollment javaEnrollment = new Enrollment(student, javaCourse, semester);
        Enrollment dbEnrollment = new Enrollment(student, dbCourse, semester);
        javaEnrollment.complete("A");
        dbEnrollment.complete("B");

        student.addEnrollment(javaEnrollment);
        student.addEnrollment(dbEnrollment);
        student.addSemester(semester);

        Path file = tempDir.resolve("student-roundtrip.json");
        StudentJsonRepository repository = new StudentJsonRepository();

        // Act
        repository.saveStudent(student, file);
        Student loaded = repository.loadStudent(file);

        // Assert - identity
        assertEquals(student.getId(), loaded.getId());
        assertEquals(student.getUserName(), loaded.getUserName());
        assertEquals(student.getProgram(), loaded.getProgram());

        // Assert - enrollments
        assertEquals(2, loaded.getEnrollments().size());

        Enrollment loadedJava = loaded.getEnrollments().get(0);
        assertEquals("DA123A", loadedJava.getCourse().getCourseCode());
        assertEquals("Java Programming", loadedJava.getCourse().getName());
        assertEquals(7.5, loadedJava.getCourse().getCredits(), 0.01);
        assertTrue(loadedJava.isCompleted(),
                "Enrollment.completed should survive a JSON round-trip");
        assertEquals("A", loadedJava.getGrade());

        // Assert - semester link
        assertEquals("HT26", loadedJava.getSemester().getName());

        // Assert - semesters on student
        assertEquals(1, loaded.getSemesters().size());
        assertEquals("HT26", loaded.getSemesters().get(0).getName());
    }

    @Test
    void findStudentShouldReturnEmptyWhenFileDoesNotExist() throws IOException {
        Path missing = tempDir.resolve("does-not-exist.json");
        StudentJsonRepository repository = new StudentJsonRepository();

        Optional<Student> result = repository.findStudent(missing);

        assertTrue(result.isEmpty());
    }

    @Test
    void loadStudentShouldIgnoreUnknownJsonFields() throws IOException {
        Path file = tempDir.resolve("student-with-extra-field.json");
        Files.writeString(file, """
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
        Student loaded = repository.loadStudent(file);

        assertEquals("mada4843", loaded.getUserName());
    }

    @Test
    void loadStudentShouldThrowOnInvalidJson() throws IOException {
        Path file = tempDir.resolve("broken.json");
        Files.writeString(file, "{ this is not json }");
        StudentJsonRepository repository = new StudentJsonRepository();

        assertThrows(IOException.class, () -> repository.loadStudent(file));
    }
}