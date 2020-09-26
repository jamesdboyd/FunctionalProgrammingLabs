package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private String name;
    private int id;
    private List<Course> courses;

    public Student(String name, int id, Course ... courses) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>(Arrays.asList(courses));
    }

    public Student(String name, int id, List<Course> courses) {
       this.name = name;
       this.id = id;
       this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /***
     * Return a copy of the Course list
     * @return
     */
    public List<Course> getCourses() {
       if(courses != null)
          return courses.stream().map(c -> c.clone()).collect(Collectors.toList());
       else
          return new ArrayList<Course>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", courses=" + courses +
                '}';
    }
}
