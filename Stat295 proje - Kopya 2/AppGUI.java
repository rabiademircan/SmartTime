package model;

import javax.swing.*;
import java.awt.*;

public class AppGUI extends JFrame {
    private JTabbedPane tabs;

    public AppGUI() {
        super("Time Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);


        UIManager.put("TabbedPane.font", new Font("Arial", Font.PLAIN, 16));
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


        ImageIcon goalIcon = new ImageIcon(new ImageIcon("icons/goal.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon gpaIcon = new ImageIcon(new ImageIcon("icons/gpa.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon studyToolIcon = new ImageIcon(new ImageIcon("icons/studytool.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon groupProjectIcon = new ImageIcon(new ImageIcon("icons/groupproject.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon notificationIcon = new ImageIcon(new ImageIcon("icons/zil.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon analyticsIcon = new ImageIcon(new ImageIcon("icons/analytics.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon weeklyOverviewIcon = new ImageIcon(new ImageIcon("icons/week.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));


        tabs.addTab(null, null, new GoalPanel(), "Goals");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, makeTab("Goals", goalIcon));

        tabs.addTab(null, null, new GPAPanel(), "GPA");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, makeTab("GPA", gpaIcon));

        tabs.addTab(null, null, new StudyToolPanel(), "Study Tool");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, makeTab("Study Tool", studyToolIcon));

        tabs.addTab(null, null, new GroupProjectPanel(), "Group Projects");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, makeTab("Group Projects", groupProjectIcon));

        tabs.addTab(null, null, new NotificationPanel(), "Notifications");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, makeTab("Notifications", notificationIcon));

        tabs.addTab(null, null, new AnalyticsPanel(), "Analytics");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, makeTab("Analytics", analyticsIcon));


        tabs.addTab(null, null, new BarChartPanel(), "Weekly Overview");
        tabs.setTabComponentAt(tabs.getTabCount() - 1, makeTab("Weekly Overview", weeklyOverviewIcon));
    }

    private JLabel makeTab(String title, ImageIcon icon) {
        JLabel label = new JLabel(title, icon, JLabel.CENTER);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 23));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}
