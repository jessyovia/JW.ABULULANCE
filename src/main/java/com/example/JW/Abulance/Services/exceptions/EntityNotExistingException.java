package com.example.JW.Abulance.Services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotExistingException extends RuntimeException {
    public EntityNotExistingException(String message) {
        super(message);
    }

}
