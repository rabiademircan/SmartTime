package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GoalPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;

    public GoalPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Title stays at 40pt
        JLabel titleLabel = new JLabel("My Study Goals", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columns = {"Done", "Title", "Type", "Due Date", "Priority"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class;
            }
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 0;
            }
        };

        table = new JTable(tableModel);
        // **1.5×** table header & cell font, and adjust row height
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 27));
        table.setFont(new Font("Arial", Font.PLAIN, 27));
        table.setRowHeight(27 + 8); // roughly 1.5 times original ~18

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField titleField = new JTextField();
        JTextField dateField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Exam", "Homework", "Project"});
        JComboBox<String> priorityBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});
        JButton addButton = new JButton("Add Goal");
        JButton deleteButton = new JButton("Delete Selected");

        // **1.5×** form labels
        JLabel titleLbl = new JLabel("Title:");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 27));
        JLabel dateLbl = new JLabel("Due Date (yyyy-MM-dd):");
        dateLbl.setFont(new Font("Arial", Font.BOLD, 27));
        JLabel typeLbl = new JLabel("Type:");
        typeLbl.setFont(new Font("Arial", Font.BOLD, 27));
        JLabel priorityLbl = new JLabel("Priority:");
        priorityLbl.setFont(new Font("Arial", Font.BOLD, 27));

        // **1.5×** inputs & buttons font
        titleField.setFont(new Font("Arial", Font.PLAIN, 27));
        dateField.setFont(new Font("Arial", Font.PLAIN, 27));
        typeBox.setFont(new Font("Arial", Font.PLAIN, 27));
        priorityBox.setFont(new Font("Arial", Font.PLAIN, 27));
        addButton.setFont(new Font("Arial", Font.BOLD, 27));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 27));

        inputPanel.add(titleLbl);
        inputPanel.add(titleField);
        inputPanel.add(dateLbl);
        inputPanel.add(dateField);
        inputPanel.add(typeLbl);
        inputPanel.add(typeBox);
        inputPanel.add(priorityLbl);
        inputPanel.add(priorityBox);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.SOUTH);

        refreshTable();

        addButton.addActionListener(e -> {
            String goalTitle = titleField.getText().trim();
            String dueDate = dateField.getText().trim();
            String goalType = (String) typeBox.getSelectedItem();
            String pr = (String) priorityBox.getSelectedItem();
            if (goalTitle.isEmpty() || dueDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill in all fields.");
                return;
            }
            Goal newGoal = new Goal(goalTitle, dueDate, goalType, pr);
            SessionManager.getCurrentUser().addGoal(newGoal);
            tableModel.addRow(new Object[]{false, goalTitle, goalType, dueDate, pr});
            titleField.setText("");
            dateField.setText("");
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String goalTitle = (String) tableModel.getValueAt(row, 1);
                List<Goal> goals = SessionManager.getCurrentUser().getGoals();
                goals.removeIf(g -> g.getTitle().equals(goalTitle));
                tableModel.removeRow(row);
            }
        });

        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow(), col = e.getColumn();
            if (col == 0 && row >= 0) {
                boolean done = (Boolean) tableModel.getValueAt(row, 0);
                String goalTitle = (String) tableModel.getValueAt(row, 1);
                for (Goal g : SessionManager.getCurrentUser().getGoals()) {
                    if (g.getTitle().equals(goalTitle)) g.setCompleted(done);
                }
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Goal g : SessionManager.getCurrentUser().getGoals()) {
            tableModel.addRow(new Object[]{
                    g.isCompleted(),
                    g.getTitle(),
                    g.getType(),
                    g.getTargetDate(),
                    g.getPriority()
            });
        }
    }
}
