package model;

import java.util.Date;
import java.util.List;

public class StudyProgram {
    private String title;
    private Date endDate;
    private Schedule schedule;
    private ScheduleType programType;

    public StudyProgram(String title, ScheduleType programType, Date endDate) {
        this.title = title;
        this.programType = programType;
        this.endDate = endDate;
        this.schedule = new Schedule();
    }

    public String getSummary() {
        return title + " (" + programType + ") - Ends on: " + endDate;
    }

    public void addActivity(Activity a) {
        schedule.addItem(a);
    }

    public void removeActivity(Activity a) {
        schedule.removeItem(a);
    }

    public List<Activity> getActivities() {
        return schedule.getItems();
    }

    public String getTitle() {
        return title;
    }
    public ScheduleType getProgramType() {
        return programType;
    }
    public Date getEndDate() {
        return endDate;
    }
    public boolean isActive() {
        return new java.util.Date().before(endDate);
    }

    public void printScheduleSummary() {
        System.out.println("Program: " + title + ", Total Activities: " + schedule.getItems().size());
    }

}

