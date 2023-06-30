package com.niit.bej.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Restaurant Already exist!")
public class RestaurantAlreadyExistsException extends Throwable {
    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
