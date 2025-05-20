package model;

import java.util.Date;

public abstract class Notification implements Notifiable {
    protected String message;
    protected int priorityLevel;
    protected Date targetTime;

    public Notification(String message, int priorityLevel, Date targetTime) {
        this.message = message;
        this.priorityLevel = priorityLevel;
        this.targetTime = targetTime;
    }

    @Override
    public void sendNotification() {
        System.out.println("Notification sent: " + message + " at " + targetTime);
    }

    public String getMessage() { return message; }
    public int getPriorityLevel() { return priorityLevel; }
    public Date getTargetTime() { return targetTime; }

    public void setMessage(String message) { this.message = message; }
    public void setPriorityLevel(int priorityLevel) { this.priorityLevel = priorityLevel; }
    public void setTargetTime(Date targetTime) { this.targetTime = targetTime; }
}
