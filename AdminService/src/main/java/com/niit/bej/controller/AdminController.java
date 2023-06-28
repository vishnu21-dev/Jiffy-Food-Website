package com.niit.bej.controller;

import com.niit.bej.domain.Restaurant;
import com.niit.bej.exception.AdminDoesNotExistException;
import com.niit.bej.exception.MerchantAlreadyExistException;
import com.niit.bej.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/registerRestaurantInAdmin")
    public ResponseEntity<?> register(@RequestBody Restaurant restaurant) {
        try {
            Restaurant registerRestaurant = adminService.addRestaurant(restaurant);
            return new ResponseEntity<>(registerRestaurant, HttpStatus.CREATED);
        } catch (MerchantAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getRestaurant/{userId}/{password}")
    public ResponseEntity<?> getMerchant(@PathVariable String userId, @PathVariable String password) {

        try {
            List<Restaurant> restaurantList = adminService.getRestaurant(userId, password);
            return new ResponseEntity<>(restaurantList, HttpStatus.OK);
        } catch (AdminDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }
}
