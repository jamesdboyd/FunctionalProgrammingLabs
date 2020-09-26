package students;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class School {

    public List<StudentGradeRecord> getGPAs() {
       List<StudentGradeRecord> recordList = new ArrayList<>();
       for( Entry<Integer, Student> e : ModelFactory.INST.getStudentMap().entrySet())
       {
          double total = e.getValue().getCourses().stream().mapToDouble(c -> c.getGrade()).sum();
          double avg = 0.0;
          if( e.getValue().getCourses().size() > 0)
             avg = total / e.getValue().getCourses().size();
          StudentGradeRecord sr = new StudentGradeRecord(e.getValue().getId(), e.getValue().getName(), avg);
          recordList.add(sr);
       }
       return recordList;
    }
    
    public StudentGradeRecord getGPA(int studentId) {
       StudentGradeRecord sgr = null;
       Student s = ModelFactory.INST.getStudentMap().get(studentId);
       if(s != null)
       {
          double total = s.getCourses().stream().mapToDouble(c -> c.getGrade()).sum();
          double avg = 0.0;
          if( s.getCourses().size() > 0)
             avg = total / s.getCourses().size();
          sgr = new StudentGradeRecord(s.getId(), s.getName(), avg);
       }
       return sgr;
    }

    public static void main(String[] args) throws Throwable {
        ModelFactory.INST.initialize();
        School s = new School();
        List<StudentGradeRecord> gradeRecords = s.getGPAs();
        for (StudentGradeRecord sgr : gradeRecords) {
            System.out.println(sgr);
        }
        StudentGradeRecord sgr = s.getGPA(10002);
        if(sgr != null)
           System.out.println(sgr.toString());
    }
}
