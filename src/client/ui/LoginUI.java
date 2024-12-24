package client.ui;

import client.controllers.ProductController;
import client.controllers.UserController;
import shared.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserController userController;

    public LoginUI(UserController userController) {
        this.userController = userController;

        // Set up the main frame
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLayout(null);

        // Custom panel with gradient background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(0x93A5CF), // Start color
                        0, getHeight(), new Color(0xE4EFE9) // End color
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(getClass().getResource("/resources/logo.png")));
        logoLabel.setBounds(120, 10, 50, 50); // Adjust size and position as needed
        backgroundPanel.add(logoLabel);

        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 80, 100, 25);
        backgroundPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 80, 150, 25);
        backgroundPanel.add(usernameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 110, 100, 25);
        backgroundPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 110, 150, 25);
        backgroundPanel.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(90, 150, 120, 30);
        backgroundPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields!");
            return;
        }

        try {
            User user = userController.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();

                // Create ProductController with a valid RemoteInterface
                ProductController productController = new ProductController(userController.getRemote());
                new ProductUI(productController).show();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}
