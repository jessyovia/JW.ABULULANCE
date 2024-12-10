package com.example.JW.Abulance.Services.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.JW.Abulance.Services.dto.ErrorDTO;
import com.example.JW.Abulance.Services.exceptions.UniqueConstraintException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ErrorDTO populateCustomError(
            HttpStatus statusCode, String summary, String message) {
        ErrorDTO customError = new ErrorDTO(
                statusCode.value(),
                summary.toUpperCase(),
                message,
                LocalDateTime.now());
        return customError;
    }

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<ErrorDTO> handleUniqueConstraintsErrors(UniqueConstraintException ex) {
        ErrorDTO error = populateCustomError(HttpStatus.CONFLICT, "Unique Constraint Violated", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
