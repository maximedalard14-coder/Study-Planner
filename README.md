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
- Implemented `Program`, `Student`, `Course`, `Semester`, and `Enrollment` models
- Implemented `StudyStatistics` and `SemesterStatistics` services
- Enhanced `StudyReport` with statistics and course overview
- Added text-file persistence through `CourseFileRepository` and `StudentFileRepository`
- Added JSON persistence through `StudentJsonRepository` using Jackson
- Refactored `StudentJsonRepository` to a path-based API
- Added JSON round-trip and error-handling tests using `@TempDir`

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

### Semester Management

- Create semesters
- Add courses to a semester
- Retrieve courses per semester

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
- Student-level and semester-level statistics

### Data Persistence

- Save and load courses to/from text files
- Save and load students to/from text files
- Save and load complete student graphs as JSON (program, courses, semesters)
- Path-based repository API (no hardcoded filenames)
- Graceful handling of missing files (`Optional`) and unknown JSON fields

### Automated Testing

- JUnit 5 unit testing
- Maven test execution
- Progression calculation tests
- Statistics tests
- Repository persistence tests (text and JSON)
- JSON round-trip tests using `@TempDir`
- Edge case validation

---

## Architecture

```text
Model
├── Program
├── Student
├── Course
├── Semester
└── Enrollment

Service
├── StudyPlanner
├── StudyStatistics
├── SemesterStatistics
└── StudyReport

Repository
├── CourseFileRepository
├── StudentFileRepository
└── StudentJsonRepository
```

---

## Domain Model

```text
Program
│
└── Student
      ├── Course[]
      ├── Semester[]
      │     └── Course[]
      └── Enrollment
             └── Course
```

---

## Example Usage

```java
StudyReport report = new StudyReport(student);
System.out.println(report.generate());
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

## Persistence

Study Planner supports two persistence strategies, both file-based.

### JSON persistence (primary)

The main persistence layer uses Jackson to serialize the full student object graph:

```text
Student
├── Program
├── Courses[]
└── Semesters[]
      └── Courses[]
```

The repository API is path-based. Callers decide where files are written; there is no dependency on a hardcoded filename.

```java
StudentJsonRepository repository = new StudentJsonRepository();

Path file = Path.of("student.json");
repository.saveStudent(student, file);

Student loaded = repository.loadStudent(file);

// Or, if a missing file is a normal case rather than an error:
Optional<Student> maybeStudent = repository.findStudent(file);
```

#### Behaviour

| Situation                                | Behaviour                                |
| ---------------------------------------- | ---------------------------------------- |
| File does not exist (`loadStudent`)      | Throws `IOException`                     |
| File does not exist (`findStudent`)      | Returns `Optional.empty()`               |
| File contains invalid JSON               | Throws `IOException`                     |
| File contains unknown fields             | Unknown fields are ignored               |
| Derived values                           | Not persisted; recalculated on load      |

Derived values such as `completedCredits` and `degreeProgress` are annotated with `@JsonIgnore` because they can always be recomputed from the authoritative data. Persisting them would risk storing stale values that disagree with the courses they were derived from.

Unknown JSON fields are ignored (`FAIL_ON_UNKNOWN_PROPERTIES = false`) so that adding new fields to a model class does not break reading of older files.

#### Example JSON

```json
{
  "id": 20060625,
  "userName": "mada4843",
  "program": {
    "name": "Data och Systemvetenskap",
    "requiredCredits": 180.0
  },
  "courses": [
    {
      "courseCode": "DA123A",
      "name": "Java Programming",
      "completed": true,
      "credits": 7.5
    }
  ],
  "semesters": [
    {
      "name": "HT2026",
      "courses": []
    }
  ]
}
```

### Text persistence (earlier implementation)

Two repositories persist data as delimited text files:

- `CourseFileRepository` — one course per line, comma-separated
- `StudentFileRepository` — a single student record, semicolon-separated

These were the first persistence implementations and are kept as a reference for how the same problem can be solved with a simpler format. JSON is the recommended approach for new code.

### Testing

`StudentJsonRepositoryTest` performs a full round-trip:

- builds a complete `Student` graph in memory
- saves it to a temporary file
- loads it back
- asserts equality of identity, programme, courses, completion status, and semester contents

Tests use JUnit 5's `@TempDir`, which creates a fresh temporary directory per test and cleans it up automatically.

---

## Design Notes

A few areas of the domain model are intentionally still under review and will be revisited before database integration:

- **Course ownership.** `Student` currently holds both a direct list of courses and a list of semesters (which themselves hold courses). Ownership should be clarified so there is a single source of truth.
- **Course completion state.** Completion is stored on `Course` and also tracked on `Enrollment`. One of these should become the authoritative source.
- **Derived values.** Degree progress and completed credits are calculated, not stored. This will remain true when persistence moves to a database.

These decisions will be documented and migrated in small steps with tests, rather than as a single large refactor.

---

## Technologies

### Current Stack

- Java 21
- Maven
- JUnit 5
- Jackson (JSON serialization / deserialization)
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
- JDBC first, then JPA/Hibernate
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
- Text-file and JSON persistence
- Maven build management
- Automated JUnit testing

Current focus:

- Finalizing JSON persistence design
- Clarifying domain ownership and completion state
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