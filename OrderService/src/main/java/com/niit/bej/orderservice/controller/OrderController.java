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

    @GetMapping("/user/getExistingUser")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            User user = orderService.getUser(emailId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user/addOrder/{date}/{price}")
    public ResponseEntity<?> addOrder(@RequestBody List<Dish> dishes, HttpServletRequest httpServletRequest, @PathVariable String date, @PathVariable double price) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User user = orderService.addOrder(dishes, emailId, date, price);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException | OrderAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/getAllOrders")
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
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId, HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            boolean deleted = orderService.deleteOrder(orderId, emailId);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } catch (UserNotFoundException | OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




    @PostMapping("user/addRestaurantToFavourites")
    public ResponseEntity<?> addRestaurantToFavourites(@RequestBody Restaurant restaurant, HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            User restaurant1 = orderService.addRestaurantToFavorites(emailId, restaurant);
            return new ResponseEntity<>(restaurant1, HttpStatus.CREATED);
        } catch (UserNotFoundException | RestaurantAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/getRestaurants")
    public ResponseEntity<?> getRestaurants(HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            List<Restaurant> restaurant = orderService.getRestaurants(emailId);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (UserNotFoundException | RestaurantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/user/addDishToFavourites")
    public ResponseEntity<?> addDishToFavourites(HttpServletRequest request, @RequestBody Dish dish) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            User dish1 = orderService.addDishToFavourites(emailId, dish);
            return new ResponseEntity<>(dish1, HttpStatus.OK);
        } catch (UserNotFoundException | DishAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/getDishes")
    public ResponseEntity<?> getDishFromFavourites(HttpServletRequest request) {

        String emailId = request.getAttribute("emailId").toString();
        try {
            List<Dish> dishFromFavourites = orderService.getDishFromFavourites(emailId);
            return new ResponseEntity<>(dishFromFavourites, HttpStatus.OK);
        } catch (DishNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/user/deleteRestaurantFromFavourites/{restaurantName}")
    public ResponseEntity<?> deleteRestaurantFromFavourites(@PathVariable String restaurantName, HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            boolean deleteRestaurant = orderService.deleteRestaurantFromFavourites(emailId, restaurantName);
            return new ResponseEntity<>(deleteRestaurant, HttpStatus.OK);
        } catch (UserNotFoundException | RestaurantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/deleteDishFromFavourites/{dishName}")
    public ResponseEntity<?> deleteDishFromFavourites(@PathVariable String dishName, HttpServletRequest request) {
        String emailId = request.getAttribute("emailId").toString();
        try {
            boolean deleteDish = orderService.deleteDishFromFavourites(emailId, dishName);
            return new ResponseEntity<>(deleteDish, HttpStatus.OK);
        } catch (UserNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {
        try {
            User user1 = orderService.updateUser(updatedUser);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


}