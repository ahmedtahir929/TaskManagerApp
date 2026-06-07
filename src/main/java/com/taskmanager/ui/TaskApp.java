package com.taskmanager.ui;

import com.taskmanager.model.TaskManager;
import com.taskmanager.exception.InvalidTaskException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TaskApp {
    private final TaskManager taskManager;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;

    public TaskApp() {
        this.taskManager = new TaskManager();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("CleanTask Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout(10, 10));

        //Top Panel: Input Field and Add Button
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        taskField = new JTextField();
        JButton addButton = new JButton("Add Task");
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        //Center Panel: Task List Display
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("My Tasks"));

        //Bottom Panel: Remove Button
        JButton removeButton = new JButton("Remove Selected");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(removeButton);

        // --- EVENT HANDLING ---
        //Lambda expression acting as an ActionEvent Listener for adding tasks
        addButton.addActionListener((ActionEvent e) -> handleAddTask());

        //Event listener for removing tasks
        removeButton.addActionListener((ActionEvent e) -> handleRemoveTask());

        //Assemble Frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null); // Center screen
        frame.setVisible(true);
    }

    private void handleAddTask() {
        String input = taskField.getText();
        try {
            //Business Logic Request
            taskManager.addTask(input);
            //Sync View with Model
            listModel.addElement(input.trim());
            taskField.setText(""); // Clear input field
        } catch (InvalidTaskException e) {
            //EXCEPTION HANDLING: Catching custom exceptions and alerting the user
            JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRemoveTask() {
        int selectedIndex = taskList.getSelectedIndex();
        try {
            //Business Logic Request
            taskManager.removeTask(selectedIndex);
            //Sync View with Model
            listModel.remove(selectedIndex);
        } catch (InvalidTaskException e) {
            //EXCEPTION HANDLING
            JOptionPane.showMessageDialog(null, e.getMessage(), "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        //Run UI on the Event Dispatch Thread (Thread-safe practice for Swing)
        SwingUtilities.invokeLater(TaskApp::new);
    }
}