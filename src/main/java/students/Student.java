package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private String name;
    private int id;
    private List<Course> courses;

    public Student(String name, int id, Course ... courses) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>(Arrays.asList(courses));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
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
