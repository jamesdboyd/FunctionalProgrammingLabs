package students;

public class StudentGradeRecord
{
   private int id;
   private String name;
   private double gpa;

   public StudentGradeRecord(int id, String name, double gpa) {
       this.id = id;
       this.name = name;
       this.gpa = gpa;
   }

   @Override
   public String toString() {
       return "StudentGradeRecord{" +
               "id=" + id +
               ", name='" + name + '\'' +
               (gpa > 0.0 ? ", gpaAverage=" + gpa : ", no courses") +
               '}';
   }
}
