package com.niit.bej.orderservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Favourite does not exists!")
public class FavouriteNotFoundException extends Exception {
    public FavouriteNotFoundException(String message) {
        super(message);
    }
}
