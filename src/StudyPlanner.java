import java.util.ArrayList;
public class StudyPlanner {
    private final ArrayList<Course> courses;

    public StudyPlanner(){
        courses = new ArrayList<>();
    }

    public void addCourse(Course course){
        courses.add(course);
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
    public double calculateCompletedCredits(){
        double totalCredits = 0;
        for(Course course : courses){
            if(course.isCompleted()){
                totalCredits += course.getCredits();
            }
        }
        return totalCredits;

    }

}
