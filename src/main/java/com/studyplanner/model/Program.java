package com.studyplanner.model;

public class Program {
    private  String name;
    private double requiredCredits;

    public Program(String name, double requiredCredits) {
        this.name = name;
        this.requiredCredits = requiredCredits;
    }
    public Program(){}

    public void setName(String name){
        this.name = name;
    }
    public void setRequiredCredits(double requiredCredits){
        this.requiredCredits = requiredCredits;
    }

    public String getName() {
        return name;
    }

    public double getRequiredCredits() {
        return requiredCredits;
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Program program = (Program) obj;
        return Double.compare(program.requiredCredits, requiredCredits) == 0 && name.equals(program.name);
    }

    @Override
    public int hashCode() {
       int result = name.hashCode();
       result = 31 * result + Double.hashCode(requiredCredits);
       return result;
    }

    @Override
    public String toString() {
        return name + "(" + requiredCredits + "hp)";
    }
}
