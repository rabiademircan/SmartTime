package model;

import javax.swing.*;
import java.awt.*;

public class GPAPanel extends JPanel {
    private JTextField totalCreditsField;
    private JTextField totalGpaField;
    private JComboBox<String> gradeBox;
    private JLabel termGpaLabel;
    private JLabel updatedCgpaLabel;
    private DefaultListModel<String> courseListModel;

    public GPAPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("GPA Manager", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        add(title, BorderLayout.NORTH);

        JPanel cgpaPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        totalCreditsField = new JTextField();
        totalGpaField = new JTextField();
        JButton saveCgpaBtn = new JButton("Save CGPA Info");

        cgpaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Previous CGPA", 0, 0, new Font("Arial", Font.BOLD, 29)));
        JLabel totalCreditsLbl = new JLabel("Total Credits:", SwingConstants.CENTER);
        totalCreditsLbl.setFont(new Font("Arial", Font.BOLD, 29));
        cgpaPanel.add(totalCreditsLbl);
        totalCreditsField.setFont(new Font("Arial", Font.PLAIN, 24));
        cgpaPanel.add(totalCreditsField);
        JLabel cgpaLbl = new JLabel("CGPA:", SwingConstants.CENTER);
        cgpaLbl.setFont(new Font("Arial", Font.BOLD, 29));
        cgpaPanel.add(cgpaLbl);
        totalGpaField.setFont(new Font("Arial", Font.PLAIN, 24));
        cgpaPanel.add(totalGpaField);

        JPanel termPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JComboBox<Integer> creditBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        creditBox.setFont(new Font("Arial", Font.PLAIN, 24));
        gradeBox = new JComboBox<>(new String[]{"AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF"});
        gradeBox.setFont(new Font("Arial", Font.PLAIN, 24));
        JButton addCourseBtn = new JButton("Add Course");
        addCourseBtn.setFont(new Font("Arial", Font.BOLD, 29));

        termPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Current Term", 0, 0, new Font("Arial", Font.BOLD, 29)));
        JLabel creditLbl = new JLabel("Credit:", SwingConstants.CENTER);
        creditLbl.setFont(new Font("Arial", Font.BOLD, 29));
        termPanel.add(creditLbl);
        termPanel.add(creditBox);
        JLabel gradeLbl = new JLabel("Grade:", SwingConstants.CENTER);
        gradeLbl.setFont(new Font("Arial", Font.BOLD, 29));
        termPanel.add(gradeLbl);
        termPanel.add(gradeBox);
        termPanel.add(new JLabel());
        termPanel.add(addCourseBtn);

        JPanel inputWrapper = new JPanel(new GridLayout(1, 2, 10, 10));
        inputWrapper.add(cgpaPanel);
        inputWrapper.add(termPanel);
        add(inputWrapper, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel(new GridLayout(2, 1));
        termGpaLabel = new JLabel("Term GPA: 0.00", SwingConstants.CENTER);
        updatedCgpaLabel = new JLabel("Updated CGPA: 0.00", SwingConstants.CENTER);
        termGpaLabel.setFont(new Font("Arial", Font.BOLD, 29));
        updatedCgpaLabel.setFont(new Font("Arial", Font.BOLD, 29));
        resultPanel.add(termGpaLabel);
        resultPanel.add(updatedCgpaLabel);
        add(resultPanel, BorderLayout.SOUTH);

        courseListModel = new DefaultListModel<>();
        JList<String> courseList = new JList<>(courseListModel);
        courseList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Added Courses", 0, 0, new Font("Arial", Font.BOLD, 29)));
        add(new JScrollPane(courseList), BorderLayout.CENTER);
    }
}
