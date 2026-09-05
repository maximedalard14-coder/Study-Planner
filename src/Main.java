import com.studyplanner.model.*;

public class Main {
    public static void main(String[] args) {
        Program program =
                new Program(
                        "Data- och systemvetenskap",
                        180);

        Student student =
                new Student(
                        1L,
                        "Maxime",
                        program);

        Course javaCourse =
                new Course(
                        "DA123A",
                        "Java Programming",
                        7.5);

        Enrollment enrollment =
                new Enrollment(
                        student,
                        javaCourse);

        System.out.println(enrollment);

        enrollment.complete("A");

        System.out.println(
                "Grade: "
                        + enrollment.getGrade());
    }
}