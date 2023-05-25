package com.niit.bej.orderservice.controller;

import com.niit.bej.orderservice.domain.Order;
import com.niit.bej.orderservice.domain.User;
import com.niit.bej.orderservice.exception.*;
import com.niit.bej.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registerUser = orderService.registerUser(user);
            return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }

    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<?> addOrder(@RequestBody Order order, @PathVariable("userId") String userId) {
        try {
            User user = orderService.addOrder(order, userId);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserNotFoundException | OrderAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getAllOrders(HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Order> orders = orderService.getAllOrders(emailId);
            return new ResponseEntity<>(orders, HttpStatus.OK);

        } catch (UserNotFoundException | OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("userId") String userId, @PathVariable("orderId") int orderId) {
        try {
            boolean deleted = orderService.deleteOrder(orderId, userId);
            return ResponseEntity.ok(deleted);
        } catch (UserNotFoundException | OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{userId}/orders/{orderId}/dishes/{dishName}")
    public ResponseEntity<?> deleteDishFromOrder(@PathVariable("userId") String userId,
                                                 @PathVariable("dishName") String dishName,
                                                 @PathVariable("orderId") int orderId) {
        try {
            boolean deleted = orderService.deleteDishFromOrder(userId, dishName, orderId);
            return ResponseEntity.ok(deleted);
        } catch (UserNotFoundException | OrderNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);


        }
    }
}