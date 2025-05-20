package model;

public class Goal extends AcademicRecord implements ProgressTrackable {
    private String title;
    private String targetDate;
    private String type;       // "Exam", "Homework", "Project"
    private String priority;   // "High", "Medium", "Low"
    private boolean completed;
    private int currentProgress;

    public Goal(String title, String targetDate, String type, String priority) {
        super("Goal");
        this.title = title;
        this.targetDate = targetDate;
        this.type = type;
        this.priority = priority;
        this.completed = false;
        this.currentProgress = 0;
    }


    public String getTitle() { return title; }
    public String getTargetDate() { return targetDate; }
    public String getType() { return type; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public int getCurrentProgress() { return currentProgress; }

    public void setTitle(String title) { this.title = title; }
    public void setTargetDate(String targetDate) { this.targetDate = targetDate; }
    public void setType(String type) { this.type = type; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setCurrentProgress(int currentProgress) { this.currentProgress = currentProgress; }

    @Override
    public void printSummary() {
        System.out.println("Goal: " + title + ", Progress: " + currentProgress + "%");
    }

    @Override
    public double trackProgress() {
        return currentProgress / 100.0;
    }

    public void printProgressBar() {
        int bars = currentProgress / 10;
        System.out.println("[" + "#".repeat(bars) + "-".repeat(10 - bars) + "] " + currentProgress + "%");
    }

    public boolean isOverdue() {
        try {
            java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(targetDate);
            return date.before(new java.util.Date());
        } catch (Exception e) {
            return false;
        }
    }
}
