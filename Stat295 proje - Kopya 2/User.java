package model;

import java.util.*;

public class User {
    private String username;
    private String password;

    private List<Goal> goals;
    private List<GroupProject> groupProjects;
    private Map<String, Integer> studyLog; // Gün: dakika
    private double totalGPA;
    private int totalCredits;
    private List<Double> currentTermGrades;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.goals = new ArrayList<>();
        this.groupProjects = new ArrayList<>();
        this.studyLog = new HashMap<>();
        this.currentTermGrades = new ArrayList<>();
        this.totalGPA = 0;
        this.totalCredits = 0;

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public List<Goal> getGoals() {
        return goals;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public void removeGoal(Goal goal) {
        goals.remove(goal);
    }


    public List<GroupProject> getGroupProjects() {
        return groupProjects;
    }

    public void addGroupProject(GroupProject project) {
        groupProjects.add(project);
    }


    public Map<String, Integer> getStudyLog() {
        return studyLog;
    }

    public void logStudy(String day, int minutes) {
        studyLog.put(day, studyLog.getOrDefault(day, 0) + minutes);
    }


    public double getTotalGPA() {
        return totalGPA;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalGPA(double totalGPA) {
        this.totalGPA = totalGPA;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Double> getCurrentTermGrades() {
        return currentTermGrades;
    }

    public void addCurrentTermGrade(double grade) {
        currentTermGrades.add(grade);
    }

    public double calculateCurrentTermGPA() {
        if (currentTermGrades.isEmpty()) return 0.0;
        double sum = 0;
        for (Double g : currentTermGrades) sum += g;
        return sum / currentTermGrades.size();
    }

    public double calculateUpdatedCGPA() {
        int newCredits = currentTermGrades.size();
        double newGPA = calculateCurrentTermGPA();
        if (newCredits == 0) return totalGPA;

        double totalPoints = totalGPA * totalCredits + newGPA * newCredits;
        int totalAllCredits = totalCredits + newCredits;

        return totalPoints / totalAllCredits;
    }
}
