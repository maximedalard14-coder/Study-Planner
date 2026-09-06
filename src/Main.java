import com.studyplanner.model.*;

public class Main {

    public static void main(String[] args) {

        Program sysdk =
                new Program(
                        "Data och Systemvetenskap",
                        180);

        Student student =
                new Student(
                        20060625L,
                        "mada4843",
                        sysdk);

        Course javaCourse =
                new Course(
                        "DA123A",
                        "Java Programming",
                        7.5);

        javaCourse.complete();

        student.addCourse(javaCourse);

        Enrollment enrollment =
                new Enrollment(
                        student,
                        javaCourse);

        enrollment.complete("A");

        System.out.println(enrollment);

        System.out.println(
                "Grade: "
                        + enrollment.getGrade());

        StudyReport report =
                new StudyReport(student);

        report.generate();
    }
}