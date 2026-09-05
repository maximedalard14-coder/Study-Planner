import com.studyplanner.model.Course;
import com.studyplanner.model.Program;
import com.studyplanner.model.Student;
import com.studyplanner.model.StudyPlanner;

public class Main {
    public static void main(String[] args) {
        Program dsvProgram =
                new Program(
                        "Data- och systemvetenskap",
                        180);

        Student student =
                new Student(
                        1L,
                        "maxime",
                        dsvProgram);

        Course javaCourse =
                new Course(
                        "DA123A",
                        "Java Programming",
                        7.5);

        javaCourse.complete();

        student.addCourse(javaCourse);

        System.out.println(
                "Completed credits: "
                        + student.getCompletedCredits());

        System.out.println(
                "Degree progress: "
                        + student.getDegreeProgress()
                        + "%");
    }

}