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
@RequestMapping("/userOrder")
public class OrderController {

    private final OrderService orderService;
    private ResponseEntity<?> responseEntity;


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

    @PostMapping("/user/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody Order order, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User user = orderService.addOrder(order, emailId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException | OrderAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/orders")
    public ResponseEntity<?> getAllOrders(HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Order> orders = orderService.getAllOrders(emailId);
            return new ResponseEntity<>(orders, HttpStatus.OK);

        } catch (UserNotFoundException | OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/user/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") int orderId, HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            boolean deleted = orderService.deleteOrder(orderId, emailId);
            return ResponseEntity.ok(deleted);
        } catch (UserNotFoundException | OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/user/deleteDishFromOrder/{orderId}/{dishName}")
    public ResponseEntity<?> deleteDishFromOrder(HttpServletRequest request, @PathVariable String dishName, @PathVariable int orderId) throws OrderNotFoundException, UserNotFoundException, DishNotFoundException {
        String emailId = request.getAttribute("emailId").toString();
        try {
            boolean dishFromOrder = orderService.deleteDishFromOrder(emailId, dishName, orderId);
            return new ResponseEntity<>(dishFromOrder, HttpStatus.ACCEPTED);

        } catch (OrderNotFoundException | UserNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}