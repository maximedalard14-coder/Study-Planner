package com.studyplanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import com.studyplanner.model.*;

class SemesterTest {

    @Test
    void shouldAddCourseToSemester() {
        Semester ht2026 = new Semester("HT26");
        Course javaCourse = new Course("DA123B", "Java programming", 7.5);

        ht2026.addCourse(javaCourse);

        assertEquals(1, ht2026.getCourses().size());
    }

    @Test
    void shouldStoreSemesterName() {
        Semester ht2026 = new Semester("HT26");
        assertEquals("HT26", ht2026.getName());
    }

    @Test
    void shouldAddMultipleCourses() {
        Semester ht2026 = new Semester("HT26");
        ht2026.addCourse(new Course("DA123A", "Java Programming", 7.5));
        ht2026.addCourse(new Course("DA234B", "Databases", 7.5));
        ht2026.addCourse(new Course("DA345C", "Algorithms", 7.5));



        assertEquals(3, ht2026.getCourses().size());
    }
}
