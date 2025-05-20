package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginPanel extends JPanel {
    private static final Map<String, User> userDatabase = new HashMap<>();

    public LoginPanel(AppGUI appFrame) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);


        JLabel header = new JLabel("Welcome to Study Manager", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setForeground(Color.DARK_GRAY);
        add(header, BorderLayout.NORTH);


        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 5));
        inputPanel.setBackground(Color.WHITE);
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
        buttonPanel.setBackground(Color.WHITE);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.setForeground(Color.BLACK);
        registerButton.setForeground(Color.BLACK);
        loginButton.setBackground(new Color(173, 216, 230));
        registerButton.setBackground(new Color(200, 200, 200));

        loginButton.setFocusPainted(false);
        registerButton.setFocusPainted(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel, BorderLayout.SOUTH);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                User user = userDatabase.get(username);
                if (user != null && user.getPassword().equals(password)) {
                    SessionManager.setCurrentUser(user); // 🔐 Oturum başlatılıyor
                    JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!");
                    appFrame.onLoginSuccess();
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Username and password cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (userDatabase.containsKey(username)) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "User already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    User newUser = new User(username, password);
                    userDatabase.put(username, newUser);
                    JOptionPane.showMessageDialog(LoginPanel.this, "User registered successfully!");
                }
            }
        });
    }
}
