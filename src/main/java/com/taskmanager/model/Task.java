package com.taskmanager.model;

import java.time.LocalDateTime;

public class Task {
    private final String title;
    private final Priority priority;
    private final long delayInSeconds;
    private final LocalDateTime createdAt;

    public Task(String title, Priority priority, long delayInSeconds) {
        this.title = title;
        this.priority = priority;
        this.delayInSeconds = delayInSeconds;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public long getDelayInSeconds() { return delayInSeconds; }

    @Override
    public String toString() {
        return String.format("[%s] %s (Executes in %ds)", priority, title, delayInSeconds);
    }
}