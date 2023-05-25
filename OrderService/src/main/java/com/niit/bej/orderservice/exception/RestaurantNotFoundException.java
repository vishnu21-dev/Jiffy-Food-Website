package com.niit.bej.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Restaurant not found in database!")
public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
