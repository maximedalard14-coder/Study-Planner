package com.studyplanner.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyplanner.model.Student;

import java.nio.file.Path;
import java.io.IOException;

public class StudentJsonRepository {

    private final ObjectMapper mapper = new ObjectMapper();

    public void saveStudent(Student student, Path path) throws IOException {

        mapper.writeValue(path.toFile(), student);
    }

    public Student loadStudent(Path path) throws IOException {

        return mapper.readValue(path.toFile() , Student.class);

    }
}