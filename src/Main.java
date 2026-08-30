public class Main {
    public static void  main(String[] args) {
        Course prog2 = new Course("Kurs Programmering 2" , 7.5);
        Course db = new Course("Kurs i databaser " , 7.5);

      StudyPlanner SUplanner = new StudyPlanner();

      SUplanner.addCourse(prog2);
      SUplanner.addCourse(db);

      SUplanner.listCourses();
    }

}