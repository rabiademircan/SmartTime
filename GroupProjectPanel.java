package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupProjectPanel extends JPanel {
    private DefaultListModel<String> projectListModel;
    private DefaultListModel<String> assignmentListModel;
    private List<GroupProject> groupProjects;
    private GroupProject currentProject;

    public GroupProjectPanel() {
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Group Projects", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 40));
        add(header, BorderLayout.NORTH);

        groupProjects = new ArrayList<>();

        projectListModel = new DefaultListModel<>();
        JList<String> projectList = new JList<>(projectListModel);
        projectList.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Projects", 0, 0, new Font("Arial", Font.BOLD, 24)));
        projectList.setFont(new Font("Arial", Font.PLAIN, 24));
        add(new JScrollPane(projectList), BorderLayout.WEST);

        assignmentListModel = new DefaultListModel<>();
        JList<String> assignmentList = new JList<>(assignmentListModel);
        assignmentList.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Assignments", 0, 0, new Font("Arial", Font.BOLD, 24)));
        assignmentList.setFont(new Font("Arial", Font.PLAIN, 24));
        add(new JScrollPane(assignmentList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField groupField = new JTextField();
        JTextField projectField = new JTextField();
        JTextField dueDateField = new JTextField();
        JTextField memberField = new JTextField();
        JTextField taskField = new JTextField();

        groupField.setFont(new Font("Arial", Font.PLAIN, 24));
        projectField.setFont(new Font("Arial", Font.PLAIN, 24));
        dueDateField.setFont(new Font("Arial", Font.PLAIN, 24));
        memberField.setFont(new Font("Arial", Font.PLAIN, 24));
        taskField.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton createProjectBtn = new JButton("Create Project");
        createProjectBtn.setFont(new Font("Arial", Font.BOLD, 24));
        JButton assignTaskBtn = new JButton("Assign Task");
        assignTaskBtn.setFont(new Font("Arial", Font.BOLD, 24));

        // Left Side (Project Creation)
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Group Name:", SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        inputPanel.add(groupField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Project Title:", SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        inputPanel.add(projectField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Due Date (yyyy-mm-dd):", SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        inputPanel.add(dueDateField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(createProjectBtn, gbc);

        // Right Side (Task Assignment)
        gbc.gridx = 2;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Member Name:", SwingConstants.RIGHT), gbc);
        gbc.gridx = 3;
        inputPanel.add(memberField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Task:", SwingConstants.RIGHT), gbc);
        gbc.gridx = 3;
        inputPanel.add(taskField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(assignTaskBtn, gbc);

        for (Component component : inputPanel.getComponents()) {
            if (component instanceof JLabel) {
                component.setFont(new Font("Arial", Font.BOLD, 24));
            }
        }

        add(inputPanel, BorderLayout.SOUTH);

        createProjectBtn.addActionListener(e -> {
            String group = groupField.getText().trim();
            String project = projectField.getText().trim();
            String due = dueDateField.getText().trim();

            if (group.isEmpty() || project.isEmpty() || due.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill in all project fields.");
                return;
            }

            currentProject = new GroupProject(group, project, due);
            groupProjects.add(currentProject);
            projectListModel.addElement(currentProject.toString());
            assignmentListModel.clear();

            groupField.setText("");
            projectField.setText("");
            dueDateField.setText("");
        });

        assignTaskBtn.addActionListener(e -> {
            if (currentProject == null) {
                JOptionPane.showMessageDialog(this, "Create a project first.");
                return;
            }

            String member = memberField.getText().trim();
            String task = taskField.getText().trim();

            if (member.isEmpty() || task.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill in member and task.");
                return;
            }

            currentProject.assignTask(member, task);
            assignmentListModel.addElement(member + ": " + task);

            memberField.setText("");
            taskField.setText("");
        });

        projectList.addListSelectionListener(e -> {
            int index = projectList.getSelectedIndex();
            if (index >= 0) {
                currentProject = groupProjects.get(index);
                assignmentListModel.clear();
                for (Map.Entry<String, String> entry : currentProject.getAssignments().entrySet()) {
                    assignmentListModel.addElement(entry.getKey() + ": " + entry.getValue());
                }
            }
        });
    }
}
