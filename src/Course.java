public class Course {
    private String name;
    private boolean completed;
    private double credits;

    public Course(String name, double credits) {
        this.name = name;
        this.credits = credits;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

   public void complete(){
        this.completed = true;
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
