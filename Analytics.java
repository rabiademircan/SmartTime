package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analytics {
    private List<Activity> studyRecords;

    public Analytics() {
        this.studyRecords = new ArrayList<>();
    }


    public void addSession(Activity activity) {
        if (activity != null) {
            studyRecords.add(activity);
        }
    }


    public Map<String, Double> calculateWeeklyStats() {
        Map<String, Double> stats = new HashMap<>();
        for (Activity activity : studyRecords) {
            String type = activity.getType().name();
            stats.put(type, stats.getOrDefault(type, 0.0) + activity.getDuration());
        }
        return stats;
    }


    public void printStudyRecords() {
        if (studyRecords.isEmpty()) {
            System.out.println("No study records found.");
        } else {
            System.out.println("Study Records:");
            for (Activity activity : studyRecords) {
                System.out.println("- " + activity);
            }
        }
    }


    public String suggestImprovements() {
        if (studyRecords.isEmpty()) {
            return "Start recording your study sessions to get recommendations!";
        }
        double totalDuration = 0;
        for (Activity a : studyRecords) {
            totalDuration += a.getDuration();
        }
        return totalDuration < 300 ? "Try studying longer or more frequently." : "Good job! Maintain consistency.";
    }
}

