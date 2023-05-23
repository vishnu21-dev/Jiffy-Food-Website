package com.niit.bej.merchantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Merchant already exists in database!")
public class MerchantAlreadyExistsException extends Exception {
    public MerchantAlreadyExistsException(String message) {
        super(message);
    }
}
