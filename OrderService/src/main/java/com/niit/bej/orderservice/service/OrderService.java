package com.niit.bej.orderservice.service;

import com.niit.bej.orderservice.domain.User;
import com.niit.bej.orderservice.exception.UserAlreadyExistsException;

public interface OrderService {

    User registerUser(User user) throws UserAlreadyExistsException;
}
