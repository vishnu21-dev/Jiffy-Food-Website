package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Merchant already exists in database!")
public class MerchantAlreadyExistException extends Exception {
    public MerchantAlreadyExistException(String message) {
        super(message);
    }
}
