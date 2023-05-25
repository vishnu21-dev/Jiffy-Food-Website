package com.niit.bej.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dish Not Found")

public class DishNotFoundException extends Exception {
    public DishNotFoundException(String message) {
        super(message);
    }
}
