package com.taskmanager.exception;

/*
* Thrown if a user attempts to schedule an identical task name that is already
* waiting in the processing queue.
*/
public class DuplicateTaskException extends Exception {
    public DuplicateTaskException(String message) {
        super(message);
    }
}