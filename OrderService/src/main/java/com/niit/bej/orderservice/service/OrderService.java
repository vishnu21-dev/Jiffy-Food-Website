package com.niit.bej.orderservice.service;

import com.niit.bej.orderservice.domain.Order;
import com.niit.bej.orderservice.domain.User;
import com.niit.bej.orderservice.exception.OrderAlreadyExistsException;
import com.niit.bej.orderservice.exception.UserAlreadyExistsException;
import com.niit.bej.orderservice.exception.UserNotFoundException;

public interface OrderService {

    User registerUser(User user) throws UserAlreadyExistsException;

    User addOrder(Order order, String userId) throws UserNotFoundException, OrderAlreadyExistsException;

}
