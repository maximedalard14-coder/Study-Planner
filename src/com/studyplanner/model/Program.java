package com.studyplanner.model;

public class Program {
    private final String name;
    private final double requiredCredits;

    public Program(String name, double requiredCredits) {
        this.name = name;
        this.requiredCredits = requiredCredits;
    }
    public String getName() {
        return name;
    }
    public double getRequiredCredits() {
        return requiredCredits;
    }
    @Override
    public String toString(){
        return name + "(" +requiredCredits + "hp)";
    }
}
