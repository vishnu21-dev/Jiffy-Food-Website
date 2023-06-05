package com.niit.bej.merchantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Restaurant not found in the database!")
public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
