package com.studyplanner.repository;

import com.studyplanner.model.Program;
import com.studyplanner.model.Student;
import com.studyplanner.model.Course;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class StudentFileRepository {

    public void saveStudent(Student student, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(student.getId() + ";" + student.getUserName() + ";"
                + student.getProgram().getName() + ";" + student.getProgram().getRequiredCredits());
        writer.close();
    }
    public Student loadStudent(String fileName)throws IOException{

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        reader.close();
        String [] parts = line.split(";");
        Long id = Long.parseLong(parts[0]);
        String userName = parts[1];
        String  programName = parts[2];
        double requiredCredits = Double.parseDouble(parts[3]);
        Program program = new Program(programName, requiredCredits );

        return new Student(id, userName, program);
    }


}
