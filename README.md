# Study Planner

A Java-based academic planning and progress management system designed to help university students manage courses, track earned credits, and monitor degree progression.

---

## Overview

Study Planner is a personal software development project created to practice object-oriented programming, software architecture, and backend development using Java.

The system helps students:

- Track completed courses
- Monitor earned credits
- Follow degree progression
- Manage enrollments
- Plan academic studies

The project is being developed incrementally, starting with a strong domain model before introducing databases, APIs, security, and advanced analytics.

---

## Current Features

### Course Management

- Create courses
- Store course code
- Store course name
- Store credit values
- Mark courses as completed

### Student Management

- Create students
- Assign courses
- Track completed credits

### Academic Programs

- Create academic programs
- Define graduation credit requirements
- Calculate degree progression

### Enrollment Management

- Register students in courses
- Track enrollment status
- Store grades

### Progress Tracking

- Calculate total credits
- Calculate completed credits
- Calculate completion percentage
- Calculate degree progression toward graduation

---

## Domain Model

```text
Program
│
└── Student
      │
      └── Enrollment
               │
               └── Course
```

---

## Core Classes

### Program

Represents an academic program and its degree requirements.

### Student

Represents a student enrolled in an academic program.

### Course

Represents a university course containing information such as:

- Course code
- Course name
- Credits
- Completion status

### Enrollment

Represents the relationship between a student and a course.

Stores:

- Student
- Course
- Grade
- Completion status

### StudyPlanner

Central application class responsible for:

- Managing courses
- Tracking credits
- Calculating completion statistics

---

## Example Usage

```java
Program program =
        new Program(
                "Data and Systems Science",
                180);

Student student =
        new Student(
                1L,
                "Maxime",
                program);

Course javaCourse =
        new Course(
                "DA123A",
                "Java Programming",
                7.5);

javaCourse.complete();

student.addCourse(javaCourse);

System.out.println(
        student.getCompletedCredits());

System.out.println(
        student.getDegreeProgress());
```

### Output

```text
7.5
4.166666666666667
```

---

## Technologies

### Current Stack

- Java
- Object-Oriented Programming (OOP)
- Git
- GitHub
- IntelliJ IDEA

### Planned Stack

- Spring Boot
- PostgreSQL
- Hibernate / JPA
- Spring Security
- Docker
- GitHub Actions
- JUnit
- Mockito

---

## Learning Objectives

This project is used to improve and demonstrate knowledge of:

- Object-oriented programming
- Domain modeling
- Software architecture
- Data structures
- Algorithms
- Version control with Git
- Database design
- Secure software development
- Automated testing
- Technical documentation

---

## Future Development

### Backend Development

- REST API using Spring Boot
- Service layer architecture
- Repository layer
- Dependency injection

### Database Integration

- PostgreSQL
- JPA/Hibernate
- Data persistence
- Database migrations

### Security

- User authentication
- Role-based authorization
- Password hashing
- Session management

### Analytics

- Academic statistics
- Credit analysis
- Degree completion forecasting
- Study pace analysis

### AI Advisor

Future versions may include recommendation systems capable of:

- Suggesting upcoming courses
- Estimating graduation dates
- Detecting study risks
- Identifying overloaded semesters

---

## Project Status

Currently under active development.

The current focus is building a robust domain model before implementing persistence, APIs, security, and advanced features.

---

## Repository Goals

This project serves as a portfolio project demonstrating:

- Java development
- Object-oriented design
- Domain modeling
- Backend engineering
- Software architecture
- Git workflows
- Database concepts
- Professional software development practices

---

## Author

**Maxime Dalard**

Data and Systems Science Student

Stockholm University
