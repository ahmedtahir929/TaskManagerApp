package com.taskmanager.exception;

/*
* Thrown if a user attempts to flood the application engine with
* more concurrent tasks than your background thread pool can handle.
*/

//RuntimeException because it's a structural state block
public class SchedulerQueueFullException extends RuntimeException {
    public SchedulerQueueFullException(String message) {
        super(message);
    }
}