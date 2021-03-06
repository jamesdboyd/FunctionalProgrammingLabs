package students;

import java.util.Objects;

public class Course {
    private String title;
    private boolean complete;
    private double grade;

    public Course(String title, boolean complete, double grade) {
        this.title = title;
        this.complete = complete;
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public boolean isComplete() {
        return complete;
    }

    public double getGrade() {
        return grade;
    }
    
    public Course clone() {
       return new Course(title, complete, grade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return complete == course.complete &&
                Double.compare(course.grade, grade) == 0 &&
                Objects.equals(title, course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, complete, grade);
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", complete=" + complete +
                ", grade=" + grade +
                '}';
    }
}
