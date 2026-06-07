package com.taskmanager.ui;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskManager;
import com.taskmanager.model.Status;

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
        mainFrame.setSize(620, 450);
        mainFrame.setLayout(new BorderLayout(10, 10));

        // Center Area: Active Task List
        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskJList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Active Tasks"));

        // Initialize the display state (Will load the "No tasks" message immediately)
        refreshListDisplay();

        // CUSTOM CELL RENDERER: Handles numbers, states, and the empty placeholder message
        taskJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // 1. Feature Check: If it's our placeholder dummy object
                if (value == null) {
                    setText("  No tasks added yet. Click \"New Task...\" to add your first task!");
                    c.setFont(c.getFont().deriveFont(Font.ITALIC));
                    c.setForeground(Color.GRAY);
                    c.setBackground(list.getBackground());
                    return c;
                }

                // 2. Regular Task Rendering Logic
                if (value instanceof Task) {
                    Task task = (Task) value;
                    setText((index + 1) + ". " + task.toString());

                    if (task.getStatus() == Status.COMPLETED) {
                        c.setBackground(new Color(212, 239, 223)); // Soft light green
                        c.setForeground(new Color(27, 94, 32));    // Dark green
                    } else if (task.getStatus() == Status.DELAYED) {
                        c.setBackground(new Color(242, 215, 213)); // Soft light red
                        c.setForeground(new Color(110, 44, 13));   // Dark red
                    } else if (!isSelected) {
                        c.setBackground(list.getBackground());     // Default fallback
                        c.setForeground(list.getForeground());
                    }
                }
                return c;
            }
        });

        // Bottom Dashboard Buttons
        JButton openFormBtn = new JButton("New Task...");
        JButton completeBtn = new JButton("Mark Completed");
        JButton delayBtn = new JButton("Mark Delayed");
        JButton removeBtn = new JButton("Remove Selected");

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        bottomPanel.add(openFormBtn);
        bottomPanel.add(completeBtn);
        bottomPanel.add(delayBtn);
        bottomPanel.add(removeBtn);

        // --- EVENT HANDLING ---
        openFormBtn.addActionListener(e -> {
            TaskFormDialog form = new TaskFormDialog(mainFrame, taskManager);
            form.setVisible(true);
            if (form.isSucceeded()) {
                refreshListDisplay();
            }
        });

        completeBtn.addActionListener(e -> {
            int selectedIndex = taskJList.getSelectedIndex();
            // Secure check: Make sure a valid task index row is chosen (not the placeholder row)
            if (selectedIndex != -1 && !taskManager.getTasks().isEmpty()) {
                Task selectedTask = taskJList.getSelectedValue();
                selectedTask.setStatus(Status.COMPLETED);
                taskJList.repaint();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please select an active task to mark completed.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        delayBtn.addActionListener(e -> {
            int selectedIndex = taskJList.getSelectedIndex();
            if (selectedIndex != -1 && !taskManager.getTasks().isEmpty()) {
                Task selectedTask = taskJList.getSelectedValue();
                selectedTask.setStatus(Status.DELAYED);
                taskJList.repaint();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please select an active task to mark delayed.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        removeBtn.addActionListener(e -> {
            int selectedIndex = taskJList.getSelectedIndex();
            if (selectedIndex != -1 && !taskManager.getTasks().isEmpty()) {
                Task selectedTask = taskJList.getSelectedValue();
                int confirmResult = JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Are you sure you want to remove this task:\n\"" + selectedTask.getTitle() + "\"",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmResult == JOptionPane.YES_OPTION) {
                    taskManager.removeTaskFromTracking(selectedIndex);
                    refreshListDisplay();
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please select an active task to remove.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        mainFrame.add(scrollPane, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void refreshListDisplay() {
        listModel.clear();
        if (taskManager.getTasks().isEmpty()) {
            // Passing a null element tells our cell renderer to render the placeholder text string safely
            listModel.addElement(null);
        } else {
            for (Task t : taskManager.getTasks()) {
                listModel.addElement(t);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskApp::new);
    }
}