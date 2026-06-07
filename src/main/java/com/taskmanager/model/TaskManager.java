package com.taskmanager.model;

import com.taskmanager.exception.DuplicateTaskException;
import com.taskmanager.exception.InvalidTaskException;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Validates a task against all structural and business rules before adding it.
     */
    public void validateAndAddTask(Task newTask) throws InvalidTaskException, DuplicateTaskException {
        // 1. Structural Check: Validate Title Input using your InvalidTaskException
        if (newTask.getTitle() == null || newTask.getTitle().trim().isEmpty()) {
            throw new InvalidTaskException("Task title cannot be empty.");
        }

        // 2: Uniqueness Check: Prevent duplicates
        for (Task t : tasks) {
            if (t.getTitle().equalsIgnoreCase(newTask.getTitle().trim())) {
                throw new DuplicateTaskException("A task named '" + newTask.getTitle().trim() + "' is already in the queue.");
            }
        }

        // If all guards pass, finalize and add the sanitized task object
        tasks.add(new Task(newTask.getTitle().trim(), newTask.getPriority()));
    }

    public void removeTaskFromTracking(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}