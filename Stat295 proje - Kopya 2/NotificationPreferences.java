package model;

public class NotificationPreferences {
    private int frequencyPerDay;
    private String preferredTimeRange;
    private boolean goalLinked;

    public NotificationPreferences(int frequencyPerDay, String preferredTimeRange, boolean goalLinked) {
        setFrequencyPerDay(frequencyPerDay);
        setPreferredTimeRange(preferredTimeRange);
        this.goalLinked = goalLinked;
    }

    public int getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(int frequencyPerDay) {
        if (frequencyPerDay >= 0) {
            this.frequencyPerDay = frequencyPerDay;
        }
    }

    public String getPreferredTimeRange() {
        return preferredTimeRange;
    }

    public void setPreferredTimeRange(String preferredTimeRange) {
        if (preferredTimeRange != null && !preferredTimeRange.isBlank()) {
            this.preferredTimeRange = preferredTimeRange;
        }
    }

    public boolean isGoalLinked() {
        return goalLinked;
    }

    public void setGoalLinked(boolean goalLinked) {
        this.goalLinked = goalLinked;
    }

    @Override
    public String toString() {
        return "NotificationPreferences{" +
                "frequencyPerDay=" + frequencyPerDay +
                ", preferredTimeRange='" + preferredTimeRange + '\'' +
                ", goalLinked=" + goalLinked +
                '}';
    }
}

