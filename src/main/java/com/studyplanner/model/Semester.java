package com.studyplanner.model;

public class Semester {
    private String name;

    public Semester(String name) {
        this.name = name;
    }

    public Semester() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Semester other = (Semester) obj;
        return name != null && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}