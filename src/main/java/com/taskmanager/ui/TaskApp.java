package com.taskmanager.ui;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskManager;

import javax.swing.*;
import java.awt.*;

public class TaskApp {
    private final TaskManager taskManager;
    private DefaultListModel<Task> listModel;
    private JList<Task> taskJList;

    public TaskApp() {
        this.taskManager = new TaskManager();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Task Queue Dashboard");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 450);
        mainFrame.setLayout(new BorderLayout(10, 10));

        // Center Area: Active Task List
        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskJList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Active Tasks"));

        // Bottom Control Panel
        JButton openFormBtn = new JButton("New Task...");
        JButton removeBtn = new JButton("Remove Selected");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        bottomPanel.add(openFormBtn);
        bottomPanel.add(removeBtn);

        // --- EVENT HANDLING ---
        openFormBtn.addActionListener(e -> {
            TaskFormDialog form = new TaskFormDialog(mainFrame, taskManager);
            form.setVisible(true);

            if (form.isSucceeded()) {
                // Instantly update UI from the internal manager state
                refreshListDisplay();
            }
        });

        removeBtn.addActionListener(e -> {
            int selectedIndex = taskJList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskManager.removeTaskFromTracking(selectedIndex);
                refreshListDisplay();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please select a task to remove.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        mainFrame.add(scrollPane, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void refreshListDisplay() {
        listModel.clear();
        for (Task t : taskManager.getTasks()) {
            listModel.addElement(t);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskApp::new);
    }
}