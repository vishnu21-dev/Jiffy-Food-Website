package com.niit.bej.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dish already present")
public class DishAlreadyPresentException extends Throwable {


    public DishAlreadyPresentException(String dishAlreadyPresent) {

    }
}
