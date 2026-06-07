package com.taskmanager.ui;

import com.taskmanager.model.Task;
import com.taskmanager.scheduler.TaskSchedulerEngine;

import javax.swing.*;
import java.awt.*;

public class TaskApp {
    private final TaskSchedulerEngine schedulerEngine;
    private DefaultListModel<Task> listModel;
    private JFrame mainFrame;

    public TaskApp() {
        this.schedulerEngine = new TaskSchedulerEngine();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        mainFrame = new JFrame("Advanced Task Scheduler");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(450, 400);
        mainFrame.setLayout(new BorderLayout(10, 10));

        //Center Area: Active Task Queue List Display
        listModel = new DefaultListModel<>();
        JList<Task> taskJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskJList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Active Scheduled Queue"));

        // Bottom Dashboard Panel
        JButton openFormBtn = new JButton("Schedule New Task...");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(openFormBtn);

        // --- EXTENDED EVENT HANDLING ---
        openFormBtn.addActionListener(e -> {
            TaskFormDialog form = new TaskFormDialog(mainFrame);
            form.setVisible(true); // Halts main thread until hidden/disposed

            if (form.isSucceeded()) {
                Task newTask = form.getCreatedTask();
                listModel.addElement(newTask);

                //Send to background executor thread
                schedulerEngine.scheduleTask(newTask, this::onTaskExecutionTriggered);
            }
        });

        mainFrame.add(scrollPane, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Callback method triggered automatically when a task's background delay timer runs out.
     */
    private void onTaskExecutionTriggered(Task completedTask) {
        //Swing UI update actions must run on the UI Event Dispatch Thread!
        SwingUtilities.invokeLater(() -> {
            listModel.removeElement(completedTask);
            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Task Executing Now:\n" + completedTask.getTitle(),
                    "Task Notification [" + completedTask.getPriority() + "]",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskApp::new);
    }
}