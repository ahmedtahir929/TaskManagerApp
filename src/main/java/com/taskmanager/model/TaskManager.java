package com.taskmanager.model;

import com.taskmanager.exception.InvalidTaskException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<String> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    //Validates and adds a task to the collection.
    public void addTask(String title) throws InvalidTaskException {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTaskException("Task title cannot be empty.");
        }

        String sanitizedTitle = title.trim();
        if (tasks.contains(sanitizedTitle)) {
            throw new InvalidTaskException("This task already exists.");
        }

        tasks.add(sanitizedTitle);
    }

    //Removes a task safely by its array index
    public void removeTask(int index) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException("Please select a valid task to remove.");
        }
        tasks.remove(index);
    }

    //Defensive copying to encapsulate the data structure.
    public List<String> getTasks() {
        return new ArrayList<>(tasks);
    }
}