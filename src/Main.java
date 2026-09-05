import com.studyplanner.model.Course;
import com.studyplanner.model.StudyPlanner;

public class Main {
    public static void main(String[] args) {
        StudyPlanner planner = new StudyPlanner();

        Course javaCourse =
                new Course(
                        "DA123A",
                        "Java Programming",
                        7.5);

        planner.addCourse(javaCourse);

        planner.completeCourse("DA123A");

        System.out.println(
                planner.calculateCompletedCredits());

        System.out.println(
                planner.getCompletionPercentage());
    }

}