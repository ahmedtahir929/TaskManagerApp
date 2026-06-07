package com.taskmanager.scheduler;

import com.taskmanager.model.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TaskSchedulerEngine {
    //Thread pool to handle asynchronous background task execution
    private final ScheduledExecutorService executorService;

    public TaskSchedulerEngine() {
        //Keeps up to 2 concurrent background worker threads alive
        this.executorService = Executors.newScheduledThreadPool(2);
    }

    /**
     * Schedules a task to run after its specified delay.
     * @param task The task to execute.
     * @param onTaskTriggered Callback routine to execute on the UI layer.
     */
    public void scheduleTask(Task task, Consumer<Task> onTaskTriggered) {
        Runnable backgroundJob = () -> {
            //Trigger the UI callback action asynchronously when the time expires
            onTaskTriggered.accept(task);
        };

        executorService.schedule(backgroundJob, task.getDelayInSeconds(), TimeUnit.SECONDS);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}