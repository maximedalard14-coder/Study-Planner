package com.studyplanner;


import com.studyplanner.model.Course;
import com.studyplanner.model.Semester;
import com.studyplanner.service.SemesterStatistics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SemesterStatisticsTest {

    @Test
    void shouldCalculateCompletedCredits() {

        Semester semester = new Semester("HT2026");

        Course javaCourse = new Course("DA123A", "Java Programming", 7.5);

        Course databaseCourse = new Course("DA234B", "Databases", 7.5);

        Course algorithmsCourse = new Course("DA234C", "Algorithms", 7.5);

        javaCourse.complete();
        databaseCourse.complete();
        algorithmsCourse.complete();

        semester.addCourse(javaCourse);
        semester.addCourse(databaseCourse);
        semester.addCourse(algorithmsCourse);

        SemesterStatistics statistics = new SemesterStatistics(semester);

        assertEquals(22.5, statistics.getCompletedCredits());
    }
}