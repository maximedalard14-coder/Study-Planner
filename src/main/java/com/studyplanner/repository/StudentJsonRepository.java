package com.studyplanner.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyplanner.model.Student;

import java.io.File;
import java.io.IOException;

public class StudentJsonRepository {

    private final ObjectMapper mapper =
            new ObjectMapper();

    public void saveStudent(
            Student student)
            throws IOException {

        mapper.writeValue(
                new File("student.json"),
                student);
    }

    public Student loadStudent() throws IOException{

        return mapper.readValue(new File( "student.json") , Student.class) ;

    }
}