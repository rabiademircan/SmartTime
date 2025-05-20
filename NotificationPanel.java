package model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class NotificationPanel extends JPanel {
    private JTextArea notificationBox;

    public NotificationPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Automatic Notifications", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        add(title, BorderLayout.NORTH);

        notificationBox = new JTextArea();
        notificationBox.setEditable(false);
        notificationBox.setFont(new Font("Monospaced", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane(notificationBox);
        add(scrollPane, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate Notifications");
        generateButton.setFont(new Font("Arial", Font.BOLD, 36)); // Yazı boyutu 3 katına çıkarıldı
        add(generateButton, BorderLayout.SOUTH);

        generateButton.addActionListener(e -> generateNotifications());
    }

    private void generateNotifications() {
        notificationBox.setText("");
        List<Goal> goals = SessionManager.getCurrentUser().getGoals();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        for (Goal g : goals) {
            String title = g.getTitle();
            String type = "Unknown";
            String priority = "Low";
            String dateStr = g.getTargetDate();

            try {
                Date dueDate = sdf.parse(dateStr);
                long diffMs = dueDate.getTime() - today.getTime();
                long diffDays = diffMs / (1000 * 60 * 60 * 24);

                if (title.toLowerCase().contains("exam")) type = "Exam";
                else if (title.toLowerCase().contains("homework")) type = "Homework";
                else if (title.toLowerCase().contains("project")) type = "Project";

                if (type.equals("Exam") && diffDays == 7) {
                    notificationBox.append("🔴 You have an exam in " + title + " within 1 week!\n");
                }

                if (type.equals("Homework") && (diffDays == 7 || diffDays == 3)) {
                    notificationBox.append("🟡 You have a homework due soon: " + title + "\n");
                }

                if (type.equals("Project") && (diffDays == 7 || diffDays == 3)) {
                    notificationBox.append("🔵 Complete the next step of your project: " + title + "\n");
                }

            } catch (Exception ex) {
                notificationBox.append("⚠️ Invalid date for: " + title + "\n");
            }
        }

        if (notificationBox.getText().isEmpty()) {
            notificationBox.setText("✅ No notifications for today.");
        }
    }
}
