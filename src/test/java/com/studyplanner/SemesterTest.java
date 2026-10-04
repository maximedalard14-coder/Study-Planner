package com.studyplanner;

import com.studyplanner.model.Semester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SemesterTest {

    @Test
    void semestersWithSameNameShouldBeEqual() {
        Semester a = new Semester("HT26");
        Semester b = new Semester("HT26");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void semestersWithDifferentNamesShouldNotBeEqual() {
        Semester a = new Semester("HT26");
        Semester b = new Semester("VT27");

        assertNotEquals(a, b);
    }
}