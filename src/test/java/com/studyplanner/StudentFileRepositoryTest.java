package com.studyplanner;

import com.studyplanner.model.Program;
import com.studyplanner.model.Student;
import com.studyplanner.repository.StudentFileRepository;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentFileRepositoryTest {

    @Test
    void shouldSaveAndLoadStudent() throws IOException{
        StudentFileRepository studentFileRepository = new StudentFileRepository();

        Program program = new Program("Data och Systemvetenskap", 180);
        Student student = new Student (1L, "mada4843" , program);
        studentFileRepository.saveStudent(student , "testStudent.txt");
        Student loadedStudent = studentFileRepository.loadStudent("testStudent.txt");

        assertEquals(student.getId(), loadedStudent.getId());
        assertEquals(student.getUserName(), loadedStudent.getUserName());
        assertEquals(student.getProgram(), loadedStudent.getProgram());
        assertEquals(student.getProgram().getRequiredCredits(), loadedStudent.getProgram().getRequiredCredits());

    }
    @AfterEach
    void cleanUp(){
        new File("testStudent.txt").delete();
    }



}
