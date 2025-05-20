package model;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WeeklyOverviewPanel extends JPanel {
    private Map<String, Integer> weekData;
    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private JComboBox<String> weekSelector;

    public WeeklyOverviewPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Weekly Study Overview", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        weekSelector = new JComboBox<>(new String[]{"This Week", "Last Week"});
        weekSelector.setFont(new Font("Arial", Font.PLAIN, 36));  // This Week ve Last Week yazıları büyük
        weekSelector.addActionListener(e -> {
            String selected = (String) weekSelector.getSelectedItem();
            loadWeekData(selected);
            repaint();
        });
        add(weekSelector, BorderLayout.NORTH);

        loadWeekData("This Week");
    }

    private void loadWeekData(String whichWeek) {
        weekData = new LinkedHashMap<>();
        for (String day : days) {
            weekData.put(day, 0);
        }

        String fileName = whichWeek.equals("This Week") ? "study_log_week_this.json" : "study_log_week_last.json";
        try {
            Path path = Path.of(fileName);
            if (Files.exists(path)) {
                String content = Files.readString(path).replaceAll("[{}\"]", "");
                for (String line : content.split(",")) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String day = parts[0].trim();
                        int minutes = Integer.parseInt(parts[1].trim());
                        if (weekData.containsKey(day)) {
                            weekData.put(day, minutes);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading weekly log: " + fileName);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (weekData == null) return;

        int width = getWidth();
        int height = getHeight() - 50;
        int barWidth = width / weekData.size();

        int max = weekData.values().stream().max(Integer::compareTo).orElse(100);

        int x = 0;
        for (Map.Entry<String, Integer> entry : weekData.entrySet()) {
            int value = entry.getValue();
            int barHeight = (int) ((double) value / max * height);

            g.setColor(new Color(100, 180, 240));
            g.fillRect(x + 10, height - barHeight, barWidth - 20, barHeight);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 18));  // Min ve gün yazı boyutunu yarıya indirdim
            g.drawString(entry.getKey(), x + barWidth / 2 - 10, height + 15);
            g.drawString(value + " min", x + barWidth / 2 - 15, height - barHeight - 5);

            x += barWidth;
        }
    }
}
