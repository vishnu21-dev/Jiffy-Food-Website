package com.niit.bej.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Order already exists!")
public class OrderAlreadyExistsException extends Exception {
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}
