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
- Added JSON persistence through `StudentJsonRepository` using Jackson
- Refactored `StudentJsonRepository` to a path-based API
- Added JSON round-trip and error-handling tests using `@TempDir`

### October 2026

- Clarified the domain model: `Enrollment` is now the single source of truth for a student's courses, completion status, and grades
- `Course` became a pure catalogue entity (no completion state)
- `Semester` became a pure planning entity (no course list)
- `Student` no longer holds a course list; courses are reached through enrollments
- Removed the legacy `StudyPlanner`, `CourseFileRepository`, and `StudentFileRepository` classes
- Enabled pretty-printed JSON output

---

## Current Features

### Course Catalogue

- Create courses with a stable `id`, course code, name, and credits
- Courses are shared across students

### Student Management

- Create students
- Assign academic programmes
- Track enrollments and semesters

### Academic Programs

- Create academic programs
- Define graduation credit requirements
- Calculate degree progression

### Enrollment Management

- Register students in courses
- Link each enrollment to a semester
- Track completion status
- Store grades

### Semester Management

- Plan semesters for a student
- Derive the courses taken in a semester from enrollments

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

- Save and load the full student graph as JSON (program, semesters, enrollments, courses)
- Path-based repository API (no hardcoded filenames)
- Pretty-printed JSON output
- Graceful handling of missing files (`Optional`) and unknown JSON fields

### Automated Testing

- JUnit 5 unit testing
- Maven test execution
- Progression calculation tests
- Statistics tests (student and semester level)
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
├── StudyStatistics
├── SemesterStatistics
└── StudyReport

Repository
└── StudentJsonRepository
```

---

## Data Model

The model follows a clear ownership rule: **`Enrollment` is the single source of truth for a student's relationship with a course.**

```text
Program
│
└── Student
      ├── semesters : Semester[]        (planning: which terms the student has planned)
      └── enrollments : Enrollment[]    (record: what the student has taken)
              │
              ├── student  : Student       (back-reference, not serialized)
              ├── course   : Course
              ├── semester : Semester
              ├── completed : boolean
              └── grade     : String
```

### Responsibilities

| Class        | Role                                                                 |
| ------------ | -------------------------------------------------------------------- |
| `Program`    | Degree programme with a name and required credits                    |
| `Student`    | Identity, programme, planned semesters, and enrollments              |
| `Course`     | Catalogue entity: `id`, code, name, credits. No completion state     |
| `Semester`   | Planning entity: a term name. No course list                         |
| `Enrollment` | Links a student, a course, and a semester; holds completion and grade |

### Why this shape

- A course is shared across students, so it cannot carry per-student state.
- A semester in the abstract has no courses; the *student's* enrolments do.
- Completion and grade belong to the student-course relationship, not the course itself.
- Derived values (`completedCredits`, `degreeProgress`) are always recalculated.

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

Completed credits: 22.5 hp
Degree progress: 12.50%

----- Statistics -----

Total courses: 3
Completed courses: 3
Remaining courses: 0

----- Course Overview -----

DA123A - Java Programming (7.5 hp) - Completed
DA234B - Databases (7.5 hp) - Completed
DA234C - Algorithms (7.5 hp) - Completed
```

---

## Persistence

Persistence is JSON-based, using Jackson.

### API

```java
StudentJsonRepository repository = new StudentJsonRepository();

Path file = Path.of("student.json");
repository.saveStudent(student, file);

Student loaded = repository.loadStudent(file);

// Or, if a missing file is a normal case rather than an error:
Optional<Student> maybeStudent = repository.findStudent(file);
```

### Behaviour

| Situation                            | Behaviour                           |
| ------------------------------------ | ----------------------------------- |
| File does not exist (`loadStudent`)  | Throws `IOException`                |
| File does not exist (`findStudent`)  | Returns `Optional.empty()`          |
| File contains invalid JSON           | Throws `IOException`                |
| File contains unknown fields         | Unknown fields are ignored          |
| Derived values                       | Not persisted; recalculated on load |

Derived values such as `completedCredits` and `degreeProgress` are annotated with `@JsonIgnore` and recomputed from enrollments on load. Persisting them would risk storing stale values.

Unknown JSON fields are ignored (`FAIL_ON_UNKNOWN_PROPERTIES = false`) so that adding new fields to a model class does not break reading of older files.

### Circular references

`Student` and `Enrollment` reference each other. This is handled with Jackson's `@JsonManagedReference` and `@JsonBackReference`:

- `Student.getEnrollments()` is the managed side and is serialized.
- `Enrollment.getStudent()` is the back side and is not serialized.
- The back-reference is restored automatically on load.

### Example JSON

```json
{
  "id" : 20060625,
  "userName" : "mada4843",
  "program" : {
    "name" : "Data och Systemvetenskap",
    "requiredCredits" : 180.0
  },
  "semesters" : [ {
    "name" : "HT26"
  } ],
  "enrollments" : [ {
    "course" : {
      "id" : 1,
      "courseCode" : "DA123A",
      "name" : "Java Programming",
      "credits" : 7.5
    },
    "semester" : {
      "name" : "HT26"
    },
    "completed" : true,
    "grade" : "A"
  } ]
}
```

### Testing

`StudentJsonRepositoryTest` performs a full round-trip:

- builds a complete `Student` graph in memory
- saves it to a temporary file
- loads it back
- asserts equality of identity, programme, enrollments, completion status, grades, and the semester link

Tests use JUnit 5's `@TempDir`, which creates a fresh temporary directory per test and cleans it up automatically.

---

## Design Notes

These decisions define where authoritative data lives in the model.

### Domain ownership

- **`Course` is a shared catalogue entity.** It carries only static information: `id`, `courseCode`, `name`, and `credits`.
- **`Enrollment` is the single source of truth.** All information about a student taking a course lives on the enrollment: which student, which course, which semester the student was registered in, completion status, and grade.
- **`Student` owns enrollments and semesters.** Courses reachable from a student are derived through enrollments.
- **`Semester` is a planning entity.** It represents a term and is owned by a student. It does not own a list of courses.

### Identifiers

- `Course` has a `Long id` supplied by the caller, preparing the model for a database primary key.
- `Enrollment` has no separate id. Its identity is the tuple `(student, course, semester)`.
- `Semester` is identified by its name.

### Constraints

- A student may not be enrolled in the same course twice within the same semester.
- A student may enrol in the same course again in a later semester, for example to retake a failed course.
- Completion is not tied to the semester of enrolment. A course may be marked as completed later than the term in which the student was registered.

### Semester naming

`Semester.name` uses a strict format: `HTxx` (autumn term) or `VTxx` (spring term), where `xx` is a two-digit year. Examples: `HT26`, `VT27`. No other formats are accepted.

### Removed

- `Course.completed` and `Course.complete()` — completion now lives on `Enrollment`.
- `Student.courses` — courses are reached through enrollments.
- `Semester.courses` — courses in a semester are derived from enrollments.
- `StudyPlanner`, `CourseFileRepository`, and `StudentFileRepository` — superseded by the new model and `StudentJsonRepository`.

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

- Core domain model with a single source of truth for enrollments
- Statistics and reporting
- JSON persistence with round-trip tests
- Maven build management
- Automated JUnit testing

Current focus:

- Hardening validation and error handling
- Expanding reporting and analytics
- Preparing for database integration

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
