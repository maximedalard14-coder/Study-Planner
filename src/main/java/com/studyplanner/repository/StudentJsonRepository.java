package com.studyplanner.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyplanner.model.Student;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Optional;

public class StudentJsonRepository {

    private final ObjectMapper mapper;

    public StudentJsonRepository() {
        this.mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
    }


    public void saveStudent(Student student, Path path) throws IOException {

        mapper.writeValue(path.toFile(), student);
    }

    public Student loadStudent(Path path) throws IOException {

        return mapper.readValue(path.toFile() , Student.class);

    }

    public Optional<Student> findStudent(Path path) throws IOException{
        if(!Files.exists(path)){
            return Optional.empty();
        }
        return Optional.of(loadStudent(path));
    }
}