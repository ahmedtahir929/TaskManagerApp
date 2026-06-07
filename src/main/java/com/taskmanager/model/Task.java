package com.taskmanager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String title;
    private final Priority priority;
    private final LocalDateTime createdAt;
    private Status status;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Task(String title, Priority priority) {
        this.title = title;
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
        this.status = Status.PENDING; // New tasks default directly to PENDING status
    }

    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        // Formats perfectly to: [HIGH] [PENDING] Clean Code Review (Created at: 14:20:05)
        return String.format("[%s] [%s] %s (Created at: %s)", priority, status, title, createdAt.format(TIME_FORMATTER));
    }
}