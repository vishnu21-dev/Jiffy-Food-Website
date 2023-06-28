package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = " Admin unavailable")
public class AdminDoesNotExistException extends Exception {
    public AdminDoesNotExistException(String message) {
        super(message);
    }
}
