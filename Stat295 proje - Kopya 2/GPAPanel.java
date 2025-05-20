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
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel cgpaPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        totalCreditsField = new JTextField();
        totalGpaField = new JTextField();
        JButton saveCgpaBtn = new JButton("Save CGPA Info");

        cgpaPanel.setBorder(BorderFactory.createTitledBorder("Previous CGPA"));
        cgpaPanel.add(new JLabel("Total Credits:"));
        cgpaPanel.add(totalCreditsField);
        cgpaPanel.add(new JLabel("CGPA:"));
        cgpaPanel.add(totalGpaField);
        cgpaPanel.add(new JLabel());
        cgpaPanel.add(saveCgpaBtn);

        JPanel termPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JComboBox<Integer> creditBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        gradeBox = new JComboBox<>(new String[]{"AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF"});
        JButton addCourseBtn = new JButton("Add Course");

        termPanel.setBorder(BorderFactory.createTitledBorder("Current Term"));
        termPanel.add(new JLabel("Credit:"));
        termPanel.add(creditBox);
        termPanel.add(new JLabel("Grade:"));
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
        termGpaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        updatedCgpaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultPanel.add(termGpaLabel);
        resultPanel.add(updatedCgpaLabel);
        add(resultPanel, BorderLayout.SOUTH);

        courseListModel = new DefaultListModel<>();
        JList<String> courseList = new JList<>(courseListModel);
        courseList.setBorder(BorderFactory.createTitledBorder("Added Courses"));
        add(new JScrollPane(courseList), BorderLayout.CENTER);

        saveCgpaBtn.addActionListener(e -> {
            try {
                int credits = Integer.parseInt(totalCreditsField.getText().trim());
                double gpa = Double.parseDouble(totalGpaField.getText().trim());

                SessionManager.getCurrentUser().setTotalCredits(credits);
                SessionManager.getCurrentUser().setTotalGPA(gpa);
                JOptionPane.showMessageDialog(this, "Previous CGPA saved.");
                updateGpaLabels();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        addCourseBtn.addActionListener(e -> {
            int credit = (int) creditBox.getSelectedItem();
            String grade = (String) gradeBox.getSelectedItem();
            double point = convertGradeToPoint(grade);

            for (int i = 0; i < credit; i++) {
                SessionManager.getCurrentUser().addCurrentTermGrade(point);
            }

            courseListModel.addElement("Credit " + credit + " - Grade " + grade);
            updateGpaLabels();
        });
    }

    private double convertGradeToPoint(String grade) {
        return switch (grade) {
            case "AA" -> 4.0;
            case "BA" -> 3.5;
            case "BB" -> 3.0;
            case "CB" -> 2.5;
            case "CC" -> 2.0;
            case "DC" -> 1.5;
            case "DD" -> 1.0;
            case "FD" -> 0.5;
            case "FF" -> 0.0;
            default -> 0.0;
        };
    }

    private void updateGpaLabels() {
        User user = SessionManager.getCurrentUser();
        double termGpa = user.calculateCurrentTermGPA();
        double newCgpa = user.calculateUpdatedCGPA();

        termGpaLabel.setText("Term GPA: " + String.format("%.2f", termGpa));
        updatedCgpaLabel.setText("Updated CGPA: " + String.format("%.2f", newCgpa));
    }
}


