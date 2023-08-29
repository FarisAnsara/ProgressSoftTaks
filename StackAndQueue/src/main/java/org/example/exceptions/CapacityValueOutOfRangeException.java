package org.example.exceptions;

public class CapacityValueOutOfRangeException extends IllegalArgumentException{
    public CapacityValueOutOfRangeException(String s) {
        super(s);
    }
}
