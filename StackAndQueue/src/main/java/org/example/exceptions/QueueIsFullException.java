package org.example.exceptions;

public class QueueIsFullException extends RuntimeException{
    public QueueIsFullException(String message) {
        super(message);
    }
}
