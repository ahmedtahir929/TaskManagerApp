package com.taskmanager.model;

import com.taskmanager.exception.DuplicateTaskException;
import com.taskmanager.exception.InvalidDelayException;
import com.taskmanager.exception.SchedulerQueueFullException;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private static final int MAX_QUEUE_CAPACITY = 10; // Defensive limit constraint

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void validateAndAddTask(Task newTask) throws DuplicateTaskException, InvalidDelayException {
        //Rule 1: Guard against system resource starvation
        if (tasks.size() >= MAX_QUEUE_CAPACITY) {
            throw new SchedulerQueueFullException("System capacity reached! Max allowed active tasks is "
                    + MAX_QUEUE_CAPACITY);
        }

        //Rule 2: Guard against bad inputs
        if (newTask.getDelayInSeconds() <= 0) {
            throw new InvalidDelayException("Execution delay must be a positive number greater than 0.");
        }

        //Rule 3: Guard against duplicates
        for (Task t : tasks) {
            if (t.getTitle().equalsIgnoreCase(newTask.getTitle())) {
                throw new DuplicateTaskException("A scheduled task named '" + newTask.getTitle() +
                        "' is already in the queue.");
            }
        }

        tasks.add(newTask);
    }

    public void removeTaskFromTracking(Task task) {
        tasks.remove(task);
    }
}