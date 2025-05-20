package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.LinkedHashMap;
import java.nio.file.*;

public class StudyToolPanel extends JPanel {
    private Timer timer;
    private int timeRemaining;
    private JLabel timeLabel;
    private JButton startButton;
    private JComboBox<String> modeBox;
    private JComboBox<Integer> customTimeBox;

    public StudyToolPanel() {
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Select Study Model", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        add(title, BorderLayout.NORTH);

        timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Monospaced", Font.PLAIN, 76));
        add(timeLabel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        modeBox = new JComboBox<>(new String[]{"Pomodoro (25 min)", "Deep Work (60 min)", "Custom"});
        customTimeBox = new JComboBox<>(new Integer[]{10, 20, 30, 40, 50, 60, 70, 80, 90});
        customTimeBox.setEnabled(false);

        modeBox.addActionListener(e -> customTimeBox.setEnabled(modeBox.getSelectedIndex() == 2));

        JLabel modeLabel = new JLabel("Mode:");
        modeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        JLabel customMinutesLabel = new JLabel("Custom Minutes:");
        customMinutesLabel.setFont(new Font("Arial", Font.BOLD, 30));
        modeBox.setFont(new Font("Arial", Font.PLAIN, 30));
        customTimeBox.setFont(new Font("Arial", Font.PLAIN, 30));
        startButton = new JButton("Start Session");
        startButton.setFont(new Font("Arial", Font.BOLD, 30));

        controlPanel.add(modeLabel);
        controlPanel.add(modeBox);
        controlPanel.add(customMinutesLabel);
        controlPanel.add(customTimeBox);
        controlPanel.add(new JLabel());
        controlPanel.add(startButton);

        add(controlPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startTimer());
    }

    private void startTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
            startButton.setText("Start Session");
            return;
        }

        int minutes = switch (modeBox.getSelectedIndex()) {
            case 0 -> 25;
            case 1 -> 60;
            case 2 -> (Integer) customTimeBox.getSelectedItem();
            default -> 0;
        };
        timeRemaining = minutes * 60;

        timer = new Timer(1000, (ActionEvent e) -> {
            timeRemaining--;
            timeLabel.setText(formatTime(timeRemaining));
            if (timeRemaining <= 0) {
                timer.stop();
                timeLabel.setText("00:00");
                startButton.setText("Start Session");
                JOptionPane.showMessageDialog(this, "Session complete!");

                saveStudyTime(minutes);
            }
        });

        timer.start();
        startButton.setText("Stop Session");
        timeLabel.setText(formatTime(timeRemaining));
    }

    private String formatTime(int sec) {
        int m = sec / 60;
        int s = sec % 60;
        return String.format("%02d:%02d", m, s);
    }

    private void saveStudyTime(int minutes) {
        try {
            String fileName = "study_log.json";
            Path path = Path.of(fileName);
            Map<String, Integer> data = new LinkedHashMap<>();

            if (Files.exists(path)) {
                String content = Files.readString(path).replaceAll("[{}\"]", "");
                for (String entry : content.split(",")) {
                    if (entry.contains(":")) {
                        String[] parts = entry.split(":");
                        data.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                    }
                }
            }

            String day = new SimpleDateFormat("E").format(new Date());


            data.put(day, data.getOrDefault(day, 0) + minutes);

            StringBuilder json = new StringBuilder("{");
            int i = 0;
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                json.append("\"").append(entry.getKey()).append("\":").append(entry.getValue());
                if (i++ < data.size() - 1) json.append(",");
            }
            json.append("}");
            Files.writeString(path, json.toString());


            User user = SessionManager.getCurrentUser();
            if (user != null) {
                user.logStudy(day, minutes);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

