package com.taskmanager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String title;
    private final Priority priority;
    private final LocalDateTime createdAt;

    // Clean Date formatter (e.g., "10:15:30" or "2026-06-07 10:15")
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Task(String title, Priority priority) {
        this.title = title;
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        // Updated to dynamically include the formatted timestamp
        return String.format("[%s] %s (Created at: %s)", priority, title, createdAt.format(TIME_FORMATTER));
    }
}