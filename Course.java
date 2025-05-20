package model;

public class Course extends AcademicRecord {
    private String name;
    private int credit;
    private double grade;

    public Course(String name, int credit, double grade) {
        super("Course");
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    @Override
    public void printSummary() {
        System.out.println("Course: " + name + ", Grade: " + grade + ", Credit: " + credit);
    }

    public double getGradePoint() {
        return grade * credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}


