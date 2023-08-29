package org.example.exceptions;

public class QueueIsEmptyException extends RuntimeException{
    public QueueIsEmptyException(String message) {
        super(message);
    }
}
