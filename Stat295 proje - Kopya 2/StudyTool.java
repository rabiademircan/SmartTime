package model;

public class StudyTool implements Schedulable {
    private String toolName;
    private int sessionLength;
    private boolean active;

    public StudyTool(String toolName, int sessionLength) {
        this.toolName = toolName;
        this.sessionLength = sessionLength;
        this.active = false;
    }

    public void startSession() {
        active = true;
        System.out.println(toolName + " session started for " + sessionLength + " minutes.");
    }

    public void pauseSession() {
        if (active) {
            active = false;
            System.out.println(toolName + " session paused.");
        }
    }

    public void endSession() {
        if (active) {
            active = false;
            System.out.println(toolName + " session ended.");
        }
    }

    @Override
    public void schedule() {
        System.out.println(toolName + " scheduled.");
    }

    @Override
    public void reschedule() {
        System.out.println(toolName + " rescheduled.");
    }

    public String getToolName() {
        return toolName;
    }
    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getSessionLength() {
        return sessionLength;
    }
    public void setSessionLength(int sessionLength) {
        this.sessionLength = sessionLength;
    }

    public boolean isActive() {
        return active;
    }
}
