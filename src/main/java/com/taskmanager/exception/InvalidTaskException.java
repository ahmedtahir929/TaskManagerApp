package com.taskmanager.exception;

/*
* Thrown if task title is left empty
*/
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}