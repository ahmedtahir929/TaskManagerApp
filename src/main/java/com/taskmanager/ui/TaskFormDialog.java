package com.taskmanager.ui;

import com.taskmanager.exception.DuplicateTaskException;
import com.taskmanager.exception.InvalidTaskException;
import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskManager;

import javax.swing.*;
import java.awt.*;

public class TaskFormDialog extends JDialog {
    private JTextField titleField;
    private JComboBox<Priority> priorityBox;
    private Task createdTask;
    private boolean succeeded = false;
    private final TaskManager taskManager;

    public TaskFormDialog(Frame parent, TaskManager taskManager) {
        // First line MUST be the super constructor call
        super(parent, "Schedule New Task", true);
        // Bind directly to the master manager reference passed from TaskApp
        this.taskManager = taskManager;
        initializeForm();
    }

    private void initializeForm() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Title Text Input
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Task Title:"), gbc);
        titleField = new JTextField(15);
        gbc.gridx = 1;
        add(titleField, gbc);

        // Row 1: Priority Dropdown
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Priority:"), gbc);
        priorityBox = new JComboBox<>(Priority.values());
        gbc.gridx = 1;
        add(priorityBox, gbc);

        // Row 3: Submission Buttons Panel (Renamed label to "Add Task" to match refactor)
        JButton submitBtn = new JButton("Add Task");
        JButton cancelBtn = new JButton("Cancel");
        JPanel btnPanel = new JPanel();
        btnPanel.add(submitBtn);
        btnPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        // Event Listeners
        submitBtn.addActionListener(e -> handleFormSubmission());
        cancelBtn.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(getParent());
    }

    private void handleFormSubmission() {
        String title = titleField.getText();
        Priority priority = (Priority) priorityBox.getSelectedItem();
        Task provisionalTask = new Task(title, priority);

        try {
            // This now successfully hits the same data engine used by TaskApp!
            taskManager.validateAndAddTask(provisionalTask);

            createdTask = provisionalTask;
            succeeded = true;
            dispose();

        } catch (InvalidTaskException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (DuplicateTaskException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Duplicate Detected", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean isSucceeded() { return succeeded; }
    public Task getCreatedTask() { return createdTask; }
}