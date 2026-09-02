public class Main {
    public static void  main(String[] args) {
        Course prog2 = new Course("Kurs Programmering 2" , 7.5);
        Course db = new Course("Kurs i databaser " , 7.5);

      StudyPlanner SUplanner = new StudyPlanner();
      prog2.complete();
      SUplanner.addCourse(prog2);
      SUplanner.addCourse(db);



      SUplanner.listCourses();
      System.out.println();
      System.out.println("Number of courses: " + SUplanner.getNumberOfCourses());
      System.out.println("Completed credits: " + SUplanner.calculateCompletedCredits() + "hp");
      System.out.println("Total credits: " + SUplanner.calculateTotalCredits() +"hp");
      System.out.println("Progress: " +  SUplanner.getCompletionPercentage() +"%");
    }

}