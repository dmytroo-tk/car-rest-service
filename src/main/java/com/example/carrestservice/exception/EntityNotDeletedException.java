package com.example.carrestservice.exception;

public class EntityNotDeletedException extends RuntimeException {
    public EntityNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
