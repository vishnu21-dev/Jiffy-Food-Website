package com.niit.bej.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Dish already exists!")
public class DishAlreadyExistsException extends Exception {
    public DishAlreadyExistsException(String message) {
        super(message);
    }
}
