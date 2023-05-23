package com.niit.bej.merchantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dish not found in database!")
public class DishNotFoundException extends Exception {

    public DishNotFoundException(String message) {
        super(message);
    }
}
