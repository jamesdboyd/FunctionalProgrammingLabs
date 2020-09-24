package students;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StudentGradeRecord {
    private int id;
    private String name;
    private double gpaSum;
    private int gpaCount;

    public StudentGradeRecord(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addGPA(double gpa) {
        gpaSum += gpa;
        gpaCount++;
    }

    @Override
    public String toString() {
        return "StudentGradeRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                (gpaCount > 0 ? ", gpaAverage=" + (gpaSum / gpaCount) : ", no courses") +
                '}';
    }
}

public class School {
    public static void populateStudentsWithCourses(Map<Integer, Student> map, String filename) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] elements = line.split(",");
                int id = Integer.parseInt(elements[0]);
                Student s = map.get(id);
                double gpa = Double.parseDouble(elements[3]);
                s.addCourse(new Course(elements[1], Boolean.parseBoolean(elements[2]), gpa));
            }
        }
    }

    public static Map<Integer, Student> makeStudents(String filename) throws IOException {
        Map<Integer, Student> rv = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename));) {
            int records = Integer.parseInt(br.readLine());
            while (records-- > 0) {
                String line = br.readLine();
                String[] elements = line.split(",");
                int id = Integer.parseInt(elements[1]);
                rv.put(id, new Student(elements[0], id));
            }
            return rv;
        }
    }

    public static List<StudentGradeRecord> getGPAs() throws IOException {
        Map<Integer, Student> roster = makeStudents("StudentData.txt");
        populateStudentsWithCourses(roster, "CourseGradeData.txt");

        Map<Integer, StudentGradeRecord> recordMap = new HashMap<>();
        for (Student s : roster.values()) {
            StudentGradeRecord record = new StudentGradeRecord(s.getId(), s.getName());
            recordMap.put(s.getId(), record);
            for (Course c : s.getCourses()) {
                record.addGPA(c.getGrade());
            }
        }
        return new ArrayList<>(recordMap.values());
    }

    public static void main(String[] args) throws Throwable {
        List<StudentGradeRecord> gradeRecords = getGPAs();
        for (StudentGradeRecord sgr : gradeRecords) {
            System.out.println(sgr);
        }
    }
}
