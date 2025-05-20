package model;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class BarChartPanel extends JPanel {

    private final Map<String, Integer> lastWeekData;
    private final Map<String, Integer> thisWeekData;
    private Map<String, Integer> currentData;
    private String currentTitle = "This Week";

    public BarChartPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        lastWeekData = loadLastWeekData();
        thisWeekData = loadThisWeekData();
        currentData = thisWeekData;


        ChartPanel chartPanel = new ChartPanel();
        add(chartPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton lastWeekButton = new JButton("Last Week");
        JButton thisWeekButton = new JButton("This Week");

        buttonPanel.add(lastWeekButton);
        buttonPanel.add(thisWeekButton);
        add(buttonPanel, BorderLayout.SOUTH);

        lastWeekButton.addActionListener(e -> {
            currentData = lastWeekData;
            currentTitle = "Last Week";
            chartPanel.repaint();
        });

        thisWeekButton.addActionListener(e -> {
            currentData = thisWeekData;
            currentTitle = "This Week";
            chartPanel.repaint();
        });
    }

    private Map<String, Integer> loadLastWeekData() {
        Map<String, Integer> data = new LinkedHashMap<>();
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int[] sampleData = {120, 30, 180, 90, 45, 0, 60}; // dakika

        for (int i = 0; i < days.length; i++) {
            data.put(days[i], sampleData[i]);
        }

        return data;
    }

    private Map<String, Integer> loadThisWeekData() {
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
            System.out.println("Error reading study_log.json: " + e.getMessage());
        }

        return data;
    }

    private class ChartPanel extends JPanel {
        public ChartPanel() {
            setPreferredSize(new Dimension(600, 350));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            int padding = 40;
            int barWidth = (width - 2 * padding) / currentData.size() - 10;
            int maxMinutes = 240;

            g2.setColor(Color.BLACK);
            g2.drawString(currentTitle, padding, 20);

            int x = padding;
            for (Map.Entry<String, Integer> entry : currentData.entrySet()) {
                String day = entry.getKey();
                int minutes = entry.getValue();
                int barHeight = (int) ((double) minutes / maxMinutes * (height - 60));


                if (minutes < 60) {
                    g2.setColor(Color.RED);
                } else if (minutes < 180) {
                    g2.setColor(Color.ORANGE);
                } else {
                    g2.setColor(Color.GREEN);
                }

                g2.fillRect(x, height - barHeight - 20, barWidth, barHeight);
                g2.setColor(Color.BLACK);
                g2.drawString(day, x + (barWidth / 4), height - 5);

                x += barWidth + 10;
            }
        }
    }
}
