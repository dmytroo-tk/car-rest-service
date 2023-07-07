package com.example.carrestservice.exception;

public class EntityNotUpdatedException extends RuntimeException {
    public EntityNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
