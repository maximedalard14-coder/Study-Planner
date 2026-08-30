public class Course {
    String name;
    double credits;

    public Course(String name, double credits) {
        this.name = name;
        this.credits = credits;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCredits(double credits) {
        this.credits = credits;
    }
    public double getCredits(){
        return this.credits;
    }
}
