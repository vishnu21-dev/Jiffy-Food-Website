package com.niit.bej.merchantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Merchant does not exists!")
public class MerchantNotFoundException extends Exception {
    public MerchantNotFoundException(String message) {
        super(message);
    }
}
