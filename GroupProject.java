package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class GroupProject {
    private String groupName;
    private String projectTitle;
    private String dueDate;
    private Map<String, String> assignments; // memberName -> task

    public GroupProject(String groupName, String projectTitle, String dueDate) {
        this.groupName = groupName;
        this.projectTitle = projectTitle;
        this.dueDate = dueDate;
        this.assignments = new LinkedHashMap<>();
    }

    public void assignTask(String memberName, String task) {
        assignments.put(memberName, task);
    }

    public Map<String, String> getAssignments() {
        return assignments;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[" + groupName + "] " + projectTitle + " (Due: " + dueDate + ")";
    }
}
