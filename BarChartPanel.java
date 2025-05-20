package model;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.LinkedHashMap;

public class BarChartPanel extends JPanel {
    private Map<String, Integer> studyData;

    public BarChartPanel() {
        setPreferredSize(new Dimension(600, 300));
        setBackground(Color.WHITE);
        studyData = loadStudyData();
    }

    private Map<String, Integer> loadStudyData() {
        Map<String, Integer> data = new LinkedHashMap<>();

        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (String day : days) {
            data.put(day, 0);
        }

        try {
            Path path = Path.of("study_log.json");
            if (Files.exists(path)) {
                String content = Files.readString(path).replaceAll("[{}\"]", "");
                for (String entry : content.split(",")) {
                    if (entry.contains(":")) {
                        String[] parts = entry.split(":");
                        String day = parts[0].trim();
                        int mins = Integer.parseInt(parts[1].trim());
                        if (data.containsKey(day)) {
                            data.put(day, mins);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading study_log.json");
        }

        return data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        int maxVal = studyData.values().stream().max(Integer::compare).orElse(60);
        int barWidth = w / studyData.size();
        int x = 0;

        for (Map.Entry<String, Integer> entry : studyData.entrySet()) {
            String day = entry.getKey();
            int minutes = entry.getValue();
            int barHeight = (int) ((double) minutes / maxVal * (h - 50));

            g.setColor(new Color(100, 180, 240));
            g.fillRect(x + 10, h - barHeight - 30, barWidth - 20, barHeight);

            g.setColor(Color.BLACK);
            g.drawString(day, x + barWidth / 2 - 10, h - 10);
            g.drawString(minutes + " min", x + barWidth / 2 - 15, h - barHeight - 35);

            x += barWidth;
        }
    }
}
