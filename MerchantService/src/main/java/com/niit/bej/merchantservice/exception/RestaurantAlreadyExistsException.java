package com.niit.bej.merchantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Restaurant already exists in database!")

public class RestaurantAlreadyExistsException extends Exception {
    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
