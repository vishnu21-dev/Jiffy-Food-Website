package com.niit.bej.merchantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Cuisine already exists in database!")
public class CuisineAlreadyExistsException extends Exception {
    public CuisineAlreadyExistsException(String message) {
        super(message);
    }
}
