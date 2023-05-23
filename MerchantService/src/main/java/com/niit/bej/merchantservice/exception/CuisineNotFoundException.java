package com.niit.bej.merchantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cuisine not found in database!")
public class CuisineNotFoundException extends Exception {

    public CuisineNotFoundException(String message) {
        super(message);
    }
}
