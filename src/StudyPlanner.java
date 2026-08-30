import java.util.ArrayList;
public class StudyPlanner {
    private ArrayList<Course> courses;

    public StudyPlanner(){
        courses = new ArrayList<>();
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public void listCourses(){
        for(Course course : courses){
            System.out.println(course.getName() +
                    " (" + course.getCredits()
            + "hp)");
        }
    }

}
