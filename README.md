# Study Planner

A Java-based academic planning and progress management system designed to help university students manage courses, track earned credits, and monitor degree progression.

## Overview

Study Planner is a personal software development project created to improve my knowledge of object-oriented programming, software architecture, testing, and backend development using Java.

The system helps students:

- Track completed courses
- Monitor earned credits
- Follow degree progression
- Manage enrollments
- Plan academic studies

The project is being developed incrementally, starting with a strong domain model before introducing databases, APIs, security, and advanced analytics.

---

## Recent Milestones

### September 2026

- Migrated the project to a Maven-based structure
- Added JUnit 5 automated testing
- Implemented Program and Enrollment models
- Implemented StudyStatistics service
- Enhanced StudyReport with statistics and course overview
- Added file-based persistence through CourseFileRepository
- Added repository persistence tests

---

## Current Features

### Course Management

- Create courses
- Store course codes and names
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

### Reporting and Analytics

- Generate study reports
- Display completed and remaining courses
- Display completed credits
- Display degree progression
- Display course overview

### Data Persistence

- Save courses to file
- Load courses from file
- Restore course data between application sessions

### Automated Testing

- JUnit 5 unit testing
- Maven test execution
- Progression calculation tests
- Statistics tests
- Repository persistence tests
- Edge case validation

---

## Architecture

```text
Model
├── Program
├── Student
├── Course
└── Enrollment

Service
├── StudyPlanner
├── StudyStatistics
└── StudyReport

Repository
└── CourseFileRepository
```

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

## Example Usage

```java
StudyReport report =
        new StudyReport(student);

report.generate();
```

Example output:

```text
===== STUDY REPORT =====

Student: mada4843
Program: Data och Systemvetenskap

Completed credits: 7.5 hp
Degree progress: 4.17%

----- Statistics -----

Total courses: 1
Completed courses: 1
Remaining courses: 0

----- Course Overview -----

DA123A - Java Programming (7.5 hp) - Completed
```

---

## Technologies

### Current Stack

- Java
- Maven
- JUnit 5
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
- Mockito

---

## Learning Objectives

This project is used to improve and demonstrate knowledge of:

- Object-oriented programming
- Domain modeling
- Software architecture
- Data structures
- Version control with Git
- Database design
- Automated testing
- Technical documentation

---

## Future Development

### Backend Development

- REST API using Spring Boot
- Service layer architecture
- Dependency injection

### Database Integration

- PostgreSQL
- JPA/Hibernate
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

The project currently includes:

- Core domain model
- Statistics and reporting
- File-based persistence
- Maven build management
- Automated JUnit testing

Current focus:

- Improving persistence and repository design
- Expanding reporting and analytics
- Preparing for future database integration

Future focus:

- Spring Boot REST API
- PostgreSQL integration
- Authentication and authorization
- Docker support

---

## Repository Goals

This project serves as a portfolio project demonstrating:

- Java development
- Object-oriented design
- Domain modeling
- Backend engineering
- Software architecture
- Git workflows
- Automated testing
- Database concepts
- Professional software development practices

---

## Author

**Maxime Dalard**

Data and Systems Science Student

Stockholm University