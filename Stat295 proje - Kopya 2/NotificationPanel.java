package model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
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
        generateButton.setFont(new Font("Arial", Font.BOLD, 36));
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
            String type = "Task";
            String priority = g.getPriority(); // "High", "Medium", "Low"
            String dateStr = g.getTargetDate();

            try {
                Date dueDate = sdf.parse(dateStr);
                long diffMs = dueDate.getTime() - today.getTime();
                long diffDays = diffMs / (1000 * 60 * 60 * 24);

                if (title.toLowerCase().contains("exam")) type = "Exam";
                else if (title.toLowerCase().contains("homework")) type = "Homework";
                else if (title.toLowerCase().contains("project")) type = "Project";

                boolean shouldNotify = false;

                switch (priority.toLowerCase()) {
                    case "high":
                        shouldNotify = true; // her gün bildir
                        break;
                    case "medium":
                        if (diffDays == 7 || diffDays == 3 || diffDays == 1) {
                            shouldNotify = true;
                        }
                        break;
                    case "low":
                        if (diffDays == 1) {
                            shouldNotify = true;
                        }
                        break;
                }

                if (shouldNotify) {
                    String emoji = switch (type) {
                        case "Exam" -> "🔴";
                        case "Homework" -> "🟡";
                        case "Project" -> "🔵";
                        default -> "📌";
                    };
                    String message = String.format(
                            "%s %s \"%s\" is due in %d day(s)! [Priority: %s]\n",
                            emoji, type, title, diffDays, priority
                    );
                    notificationBox.append(message);
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
