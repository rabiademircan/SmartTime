package model;

import java.util.Date;

public abstract class Activity implements Schedulable {

    private String title;
    private ActivityType type;
    private Date dateTime;
    private int duration;

    public Activity(String title, ActivityType type, Date dateTime, int duration) {
        this.title = title;
        this.type = type;
        this.dateTime = dateTime;
        this.duration = duration;
    }

    public abstract void markCompleted();

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime){
        this.dateTime=dateTime;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration){
        this.duration=duration;
    }
    @Override
    public String toString() {
        return title + " (" + type + ") - " + duration + " mins";
    }



        @Override
        public void schedule() {
            System.out.println(title + " scheduled.");
        }

        @Override
        public void reschedule() {
            System.out.println(title + " rescheduled.");
        }
    }

