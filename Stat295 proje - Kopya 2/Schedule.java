package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedule {
    private List<Activity> items;

    public Schedule() {
        this.items = new ArrayList<>();
    }

    public void addItem(Activity activity) {
        if (activity != null) {
            items.add(activity);
        }
    }

    public void removeItem(Activity activity) {
        if (items.contains(activity)) {
            items.remove(activity);
            System.out.println("Activity removed: " + activity.getTitle());
        }
    }

    public List<Activity> getItems() {
        return Collections.unmodifiableList(items);
    }
    public List<Activity> getActivitiesByType(ActivityType type) {
        List<Activity> result = new ArrayList<>();
        for (Activity a : items) {
            if (a.getType() == type) result.add(a);
        }
        return result;
    }


    public void setItems(List<Activity> items) {
        if (items != null) {
            this.items = items;
        }
    }

    public void clearSchedule() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "items=" + items +
                '}';
    }
}