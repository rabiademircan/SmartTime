package model;

import javax.swing.*;
import java.awt.*;

public class AppGUI extends JFrame {
    private JTabbedPane tabs;

    public AppGUI() {
        super("Time Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);

        UIManager.put("TabbedPane.font", new Font("Arial", Font.BOLD, 48));
        UIManager.put("TabbedPane.selected", Color.LIGHT_GRAY);
        UIManager.put("TabbedPane.foreground", Color.WHITE);
        UIManager.put("TabbedPane.background", Color.DARK_GRAY);

        tabs = new JTabbedPane();

        tabs.addTab("Login", new LoginPanel(this));

        add(tabs);
        setVisible(true);
    }

    public void onLoginSuccess() {
        tabs.removeAll();

        // Icons (3x size)
        ImageIcon goalIcon = new ImageIcon(new ImageIcon("C:/Users/Muhammet Furkan Koç/Desktop/Stat295 proje/icons/goal.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon gpaIcon = new ImageIcon(new ImageIcon("C:/Users/Muhammet Furkan Koç/Desktop/Stat295 proje/icons/gpa.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon studyToolIcon = new ImageIcon(new ImageIcon("C:/Users/Muhammet Furkan Koç/Desktop/Stat295 proje/icons/studytool.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon groupProjectIcon = new ImageIcon(new ImageIcon("C:/Users/Muhammet Furkan Koç/Desktop/Stat295 proje/icons/groupproject.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon notificationIcon = new ImageIcon(new ImageIcon("C:/Users/Muhammet Furkan Koç/Desktop/Stat295 proje/icons/zil.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon analyticsIcon = new ImageIcon(new ImageIcon("C:/Users/Muhammet Furkan Koç/Desktop/Stat295 proje/icons/analytics.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        ImageIcon weeklyOverviewIcon = new ImageIcon(new ImageIcon("C:/Users/Muhammet Furkan Koç/Desktop/Stat295 proje/icons/week.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        // Create tab labels
        JLabel goalLabel = new JLabel("Goals", goalIcon, JLabel.CENTER);
        JLabel gpaLabel = new JLabel("GPA", gpaIcon, JLabel.CENTER);
        JLabel studyLabel = new JLabel("Study Tool", studyToolIcon, JLabel.CENTER);
        JLabel groupLabel = new JLabel("Group Projects", groupProjectIcon, JLabel.CENTER);
        JLabel notificationLabel = new JLabel("Notifications", notificationIcon, JLabel.CENTER);
        JLabel analyticsLabel = new JLabel("Analytics", analyticsIcon, JLabel.CENTER);
        JLabel weeklyLabel = new JLabel("Weekly Overview", weeklyOverviewIcon, JLabel.CENTER);

        // Positioning text and icons
        goalLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        goalLabel.setForeground(Color.WHITE);
        gpaLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        gpaLabel.setForeground(Color.WHITE);
        studyLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        studyLabel.setForeground(Color.WHITE);
        groupLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        groupLabel.setForeground(Color.WHITE);
        notificationLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        notificationLabel.setForeground(Color.WHITE);
        analyticsLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        analyticsLabel.setForeground(Color.WHITE);
        weeklyLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        weeklyLabel.setForeground(Color.WHITE);

        // Adding tabs with custom labels
        tabs.addTab(null, null, new GoalPanel(), "Goals");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, goalLabel);
        tabs.addTab(null, null, new GPAPanel(), "GPA");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, gpaLabel);
        tabs.addTab(null, null, new StudyToolPanel(), "Study Tool");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, studyLabel);
        tabs.addTab(null, null, new GroupProjectPanel(), "Group Projects");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, groupLabel);
        tabs.addTab(null, null, new NotificationPanel(), "Notifications");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, notificationLabel);
        tabs.addTab(null, null, new AnalyticsPanel(), "Analytics");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, analyticsLabel);
        tabs.addTab(null, null, new WeeklyOverviewPanel(), "Weekly Overview");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, weeklyLabel);
        goalLabel.setFont(new Font("Arial", Font.BOLD, 23));
        gpaLabel.setFont(new Font("Arial", Font.BOLD, 23));
        studyLabel.setFont(new Font("Arial", Font.BOLD, 23));
        groupLabel.setFont(new Font("Arial", Font.BOLD, 23));
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 23));
        analyticsLabel.setFont(new Font("Arial", Font.BOLD, 23));
        weeklyLabel.setFont(new Font("Arial", Font.BOLD, 23));

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}
