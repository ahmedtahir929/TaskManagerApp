package com.taskmanager;

import com.taskmanager.model.TaskManager;
import com.taskmanager.exception.InvalidTaskException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    public void setUp() {
        manager = new TaskManager(); // Fresh instance before every test
    }

    @Test
    public void testAddValidTask() throws InvalidTaskException {
        manager.addTask("Study Java Exception Handling");
        assertEquals(1, manager.getTasks().size());
        assertTrue(manager.getTasks().contains("Study Java Exception Handling"));
    }

    @Test
    public void testAddEmptyTaskThrowsException() {
        assertThrows(InvalidTaskException.class, () -> {
            manager.addTask("   "); // Testing whitespace trimming optimization
        });
    }

    @Test
    public void testAddDuplicateTaskThrowsException() throws InvalidTaskException {
        manager.addTask("Refactor Code");
        assertThrows(InvalidTaskException.class, () -> {
            manager.addTask("Refactor Code");
        });
    }

    @Test
    public void testRemoveTaskSuccessfully() throws InvalidTaskException {
        manager.addTask("Submit Assignment");
        manager.removeTask(0);
        assertEquals(0, manager.getTasks().size());
    }

    @Test
    public void testRemoveInvalidIndexThrowsException() {
        assertThrows(InvalidTaskException.class, () -> {
            manager.removeTask(99); // Index out of bounds verification
        });
    }
}