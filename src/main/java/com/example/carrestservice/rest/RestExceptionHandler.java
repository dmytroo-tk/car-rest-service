package com.example.carrestservice.rest;

import com.example.carrestservice.exception.EntityNotCreatedException;
import com.example.carrestservice.exception.EntityNotDeletedException;
import com.example.carrestservice.exception.EntityNotFoundException;
import com.example.carrestservice.exception.EntityNotUpdatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotCreatedException.class)
    public ResponseEntity<String> handleEntityNotCreatedException(EntityNotCreatedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotUpdatedException.class)
    public ResponseEntity<String> handleEntityNotUpdatedException(EntityNotUpdatedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotDeletedException.class)
    public ResponseEntity<String> handleEntityNotDeletedException(EntityNotDeletedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
