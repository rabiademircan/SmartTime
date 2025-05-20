package model;

import java.util.ArrayList;
import java.util.List;

public class GPA extends AcademicRecord {
    private List<Course> courses;

    public GPA() {
        super("GPA");
        this.courses = new ArrayList<>();
    }

    @Override
    public void printSummary() {
        System.out.println("Total GPA: " + calculateGPA());
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;
        for (Course course : courses) {
            totalPoints += course.getGradePoint();
            totalCredits += course.getCredit();
        }
        return totalCredits == 0 ? 0 : totalPoints / totalCredits;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
