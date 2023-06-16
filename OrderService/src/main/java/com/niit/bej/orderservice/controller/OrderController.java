package com.niit.bej.orderservice.controller;

import com.niit.bej.orderservice.domain.Dish;
import com.niit.bej.orderservice.domain.Order;
import com.niit.bej.orderservice.domain.Restaurant;
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

    @GetMapping("/user/getUser")
    public ResponseEntity<?> getExistingUser(HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            User user = orderService.getUser(emailId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("user/addRestaurants/")
    public ResponseEntity<?> addRestaurant(HttpServletRequest request, @RequestBody Restaurant restaurant) {
        try {
            String userId = request.getAttribute("emailId").toString();

            Restaurant addedRestaurant = orderService.addRestaurant(userId, restaurant);
            return new ResponseEntity<>(addedRestaurant, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RestaurantAlreadyPresentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("user/getRestaurants")
    public ResponseEntity<?> getRestaurants(HttpServletRequest request) {
        try {
            String userId = request.getAttribute("emailId").toString();

            List<Restaurant> restaurantList = orderService.getRestaurant(userId);
            return new ResponseEntity<>(restaurantList, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No restaurants found: " + e.getMessage());

        }
    }

    @PostMapping("user/dishes")
    public ResponseEntity<?> addDish(HttpServletRequest request, @RequestBody Dish dish) {
        try {
            String userId = request.getAttribute("emailId").toString();

            Dish addedDish = orderService.addDish(userId, dish);
            return new ResponseEntity<>(addedDish, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (DishAlreadyPresentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("user/getDishes")
    public ResponseEntity<?> getDishes(HttpServletRequest request) {
        try {
            String userId = request.getAttribute("emailId").toString();

            List<Dish> dishList = orderService.getDish(userId);
            return new ResponseEntity<>(dishList, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("user/restaurants/{restaurant}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String restaurant, HttpServletRequest request) {
        try {
            String userId = request.getAttribute("emailId").toString();

            boolean deleted = orderService.deleteRestaurant(userId, restaurant);
            return new ResponseEntity<>(deleted, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("user/dishes/{dishName}")
    public ResponseEntity<?> deleteDish(@PathVariable String dishName, HttpServletRequest request) {
        try {
            String userId = request.getAttribute("emailId").toString();

            boolean deleted = orderService.deleteDish(userId, dishName);
            return new ResponseEntity<>(deleted, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}