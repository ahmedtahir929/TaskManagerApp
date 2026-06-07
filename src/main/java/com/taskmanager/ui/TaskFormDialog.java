package com.taskmanager.ui;

import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.exception.InvalidTaskException;

import javax.swing.*;
import java.awt.*;

public class TaskFormDialog extends JDialog {
    private JTextField titleField;
    private JComboBox<Priority> priorityBox;
    private JSpinner delaySpinner;
    private Task createdTask;
    private boolean succeeded = false;

    public TaskFormDialog(Frame parent) {
        super(parent, "Schedule New Task", true);
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

        // Row 2: Delay Timer Spinner
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Delay (Seconds):"), gbc);
        delaySpinner = new JSpinner(new SpinnerNumberModel(5, 1, 3600, 1));
        gbc.gridx = 1;
        add(delaySpinner, gbc);

        // Row 3: Submission Buttons Panel
        JButton submitBtn = new JButton("Schedule");
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
        try {
            String title = titleField.getText().trim();
            if (title.isEmpty()) {
                throw new InvalidTaskException("Form validation failed: Title required.");
            }

            Priority priority = (Priority) priorityBox.getSelectedItem();
            int delay = (Integer) delaySpinner.getValue();

            createdTask = new Task(title, priority, delay);
            succeeded = true;
            dispose();
        } catch (InvalidTaskException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isSucceeded() { return succeeded; }
    public Task getCreatedTask() { return createdTask; }
}