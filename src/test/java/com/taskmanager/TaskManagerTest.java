package com.taskmanager;

import com.taskmanager.model.Priority;
import com.taskmanager.model.Status;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskManager;
import com.taskmanager.exception.InvalidTaskException;
import com.taskmanager.exception.DuplicateTaskException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    public void setUp() {
        // Enforces fresh test isolation before every single test run
        manager = new TaskManager();
    }

    @Test
    public void testAddValidTaskInstantly() throws InvalidTaskException, DuplicateTaskException {
        Task validTask = new Task("Complete Homework", Priority.HIGH);

        manager.validateAndAddTask(validTask);

        assertEquals(1, manager.getTasks().size(), "The task list size should be exactly 1.");
        assertEquals("Complete Homework", manager.getTasks().getFirst().getTitle(), "The stored title should be correct.");
        assertEquals(Priority.HIGH, manager.getTasks().getFirst().getPriority(), "The priority should match.");
        assertNotNull(manager.getTasks().getFirst().getCreatedAt(), "The creation timestamp must be automatically generated.");
    }

    @Test
    public void testEmptyTaskTitleThrowsInvalidTaskException() {
        // Enforces validation pass against titles made entirely of blank spaces
        Task blankTask = new Task("     ", Priority.MEDIUM);

        assertThrows(InvalidTaskException.class, () -> {
            manager.validateAndAddTask(blankTask);
        }, "An empty or whitespace-only task title must trigger an InvalidTaskException.");
    }

    @Test
    public void testNullTaskTitleThrowsInvalidTaskException() {
        Task nullTitleTask = new Task(null, Priority.LOW);

        assertThrows(InvalidTaskException.class, () -> {
            manager.validateAndAddTask(nullTitleTask);
        }, "A null task title must trigger an InvalidTaskException.");
    }

    @Test
    public void testDuplicateTaskNameThrowsException() throws InvalidTaskException, DuplicateTaskException {
        Task task1 = new Task("Review Code", Priority.MEDIUM);
        // Case-insensitivity check and surrounding padding whitespace verification
        Task duplicateTask = new Task("  review code  ", Priority.LOW);

        manager.validateAndAddTask(task1);

        assertThrows(DuplicateTaskException.class, () -> {
            manager.validateAndAddTask(duplicateTask);
        }, "Adding a task with an identical name must trigger a DuplicateTaskException.");
    }

    @Test
    public void testRemoveTaskFromQueueSuccessfully() throws InvalidTaskException, DuplicateTaskException {
        Task sampleTask = new Task("Submit Project Assignment", Priority.HIGH);
        manager.validateAndAddTask(sampleTask);

        // Remove item from tracked array position index
        manager.removeTaskFromTracking(0);

        assertEquals(0, manager.getTasks().size(), "The active task queue should be empty after removal.");
    }

    @Test
    public void testNewTaskDefaultsToPendingStatus() throws InvalidTaskException, DuplicateTaskException {
        Task testTask = new Task("Submit Code Artifacts", Priority.HIGH);
        manager.validateAndAddTask(testTask);

        assertEquals(Status.PENDING, manager.getTasks().getFirst().getStatus(), "Fresh tasks must launch under PENDING status flags.");
    }

    @Test
    public void testMarkTaskAsCompletedTogglesStatus() throws InvalidTaskException, DuplicateTaskException {
        Task testTask = new Task("Run Testing Lifecycles", Priority.MEDIUM);
        manager.validateAndAddTask(testTask);

        Task storedTask = manager.getTasks().getFirst();
        storedTask.setStatus(Status.COMPLETED);

        assertEquals(Status.COMPLETED, manager.getTasks().getFirst().getStatus(), "The structural task status record must accurately update to COMPLETED.");
    }

    @Test
    public void testMarkTaskAsDelayedTogglesStatus() throws InvalidTaskException, DuplicateTaskException {
        Task testTask = new Task("Refactor Final Module", Priority.LOW);
        manager.validateAndAddTask(testTask);

        Task storedTask = manager.getTasks().getFirst();
        storedTask.setStatus(Status.DELAYED);

        assertEquals(Status.DELAYED, manager.getTasks().getFirst().getStatus(), "The structural task status record must accurately update to DELAYED.");
    }

    @Test
    public void testRemoveTaskWithInvalidIndexDoesNotCrash() {
        // Validates that safe index bounds checks don't throw unexpected runtime out-of-bounds crashes
        assertDoesNotThrow(() -> {
            manager.removeTaskFromTracking(99);
            manager.removeTaskFromTracking(-1);
        }, "Removing an invalid index must be ignored safely without crashing the system.");
    }
}