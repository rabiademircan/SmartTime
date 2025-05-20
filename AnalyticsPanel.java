package model;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class AnalyticsPanel extends JPanel {
    private JTextArea reportArea;

    public AnalyticsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Daily Study Analytics", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        add(title, BorderLayout.NORTH);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 30));
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        JButton analyzeBtn = new JButton("Analyze Today");
        analyzeBtn.setFont(new Font("Arial", Font.BOLD, 36)); // Yazı boyutu 3 katına çıkarıldı
        add(analyzeBtn, BorderLayout.SOUTH);

        analyzeBtn.addActionListener(e -> analyzeStudy());
    }

    private void analyzeStudy() {
        reportArea.setText("");

        User user = SessionManager.getCurrentUser();
        Map<String, Integer> log = user.getStudyLog();
        String todayKey = new SimpleDateFormat("E").format(new Date()); // "Mon", "Tue", etc.

        int minutes = log.getOrDefault(todayKey, 0);
        String suggestion;

        if (minutes >= 180) {
            suggestion = "🌟 Excellent work today! You're hitting your goals.";
        } else if (minutes >= 90) {
            suggestion = "👍 Nice effort! Try pushing a bit further tomorrow.";
        } else if (minutes > 0) {
            suggestion = "🔄 Keep going! Try to stay focused longer today.";
        } else {
            suggestion = "😴 No study time recorded. Time to get to work!";
        }

        reportArea.setText(
                "📅 Today: " + todayKey + "\n"
                        + "⏱ Study time: " + minutes + " minutes\n\n"
                        + "💬 Suggestion:\n" + suggestion
        );
    }
}
