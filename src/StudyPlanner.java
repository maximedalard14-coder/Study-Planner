import java.util.ArrayList;
public class StudyPlanner {
    private final ArrayList<Course> courses;

    public StudyPlanner(){
        courses = new ArrayList<>();
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public void completeCourse(String courseName){
        for(Course course : courses ){
            if(course.getName().equals(courseName)){
                course.complete();
            }
        }
    }

    public void listCourses(){

        for(Course course : courses){
            String status = course.isCompleted() ? "Completed" : "Not completed";
            System.out.println(course.getName() +
                    " (" + course.getCredits()
            + "hp)" +" - " + status);
        }
    }
    public  int getNumberOfCourses(){
        return courses.size();
    }
    public double calculateTotalCredits(){
        double totalCredits=0;
        for(Course course : courses){
            totalCredits += course.getCredits();
        }
        return totalCredits;
    }

    public double calculateCompletedCredits(){
        double totalCredits = 0;
        for(Course course : courses){
            if(course.isCompleted()){
                totalCredits += course.getCredits();
            }
        }
        return totalCredits;

    }
    public double getCompletionPercentage(){
        double total = calculateTotalCredits();
        if(total == 0){
            return 0.0;
        }
        return (calculateCompletedCredits()  / total) * 100;
    }

}
