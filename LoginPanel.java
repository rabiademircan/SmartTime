package model;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LoginPanel extends JPanel {
    private static final Map<String, User> userDatabase = new HashMap<>();

    public LoginPanel(AppGUI appFrame) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Welcome to Study Manager", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 5));
        JLabel userLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        inputPanel.add(userLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passLabel);
        inputPanel.add(passwordField);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton registerBtn = new JButton("Register");
        JButton loginBtn = new JButton("Login");

        registerBtn.setBackground(new Color(100, 160, 240));
        registerBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(new Color(40, 120, 200));
        loginBtn.setForeground(Color.WHITE);
        buttonPanel.add(registerBtn);
        buttonPanel.add(loginBtn);

        add(buttonPanel, BorderLayout.SOUTH);


        registerBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            } else {
                User newUser = new User(username, password);
                userDatabase.put(username, newUser);
                JOptionPane.showMessageDialog(this, "Registration successful!");
            }
        });


        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (!userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "User not found.");
                return;
            }

            User user = userDatabase.get(username);
            if (!user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Incorrect password.");
                return;
            }


            SessionManager.login(user);
            JOptionPane.showMessageDialog(this, "Welcome, " + user.getUsername() + "!");
            appFrame.onLoginSuccess();
        });
    }
}


