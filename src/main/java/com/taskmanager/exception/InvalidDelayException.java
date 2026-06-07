package com.taskmanager.exception;

/*
* Thrown if a user manually bypasses form rules or inputs an unrealistic,
* negative, or zero scheduling execution time context.
*/
public class InvalidDelayException extends Exception {
    public InvalidDelayException(String message) {
        super(message);
    }
}