package com.niit.bej.merchantservice.controller;

import com.niit.bej.merchantservice.domain.Dish;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.domain.Restaurant;
import com.niit.bej.merchantservice.exception.*;
import com.niit.bej.merchantservice.service.MerchantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    private ResponseEntity<?> responseEntity;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/registerMerchant")
    public ResponseEntity<?> register(@RequestBody Merchant merchant) {
        try {
            Merchant registerMerchant = merchantService.register(merchant);
            return new ResponseEntity<>(registerMerchant, HttpStatus.CREATED);
        } catch (MerchantAlreadyExistsException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/merchant/getMerchant")
    public ResponseEntity<?> getMerchant(HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Merchant existingMerchant = merchantService.getMerchant(emailId);
            return new ResponseEntity<>(existingMerchant, HttpStatus.OK);
        } catch (MerchantNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/merchant/addDishes/{restaurantName}")
    public ResponseEntity<?> addDishes(@RequestBody Dish dish, @PathVariable String restaurantName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Restaurant addDishes = merchantService.addDishes(dish, restaurantName, emailId);
            return new ResponseEntity<>(addDishes, HttpStatus.OK);
        } catch (MerchantNotFoundException | RestaurantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DishAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAllDishes")
    public ResponseEntity<?> getAllDishes() {
        try {
            List<Dish> dishList = merchantService.getAllDishes();
            return new ResponseEntity<>(dishList, HttpStatus.OK);
        } catch (DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/merchant/getAllDishesFromRestaurant/{restaurantName}")
    public ResponseEntity<?> getAllDishesFromRestaurant(@PathVariable String restaurantName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Dish> dishList = merchantService.getAllDishesFromRestaurant(restaurantName, emailId);
            return new ResponseEntity<>(dishList, HttpStatus.OK);
        } catch (MerchantNotFoundException | RestaurantNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/merchant/updateDish/{restaurantName}")
    public ResponseEntity<?> updateDish(@RequestBody Dish dish, @PathVariable String restaurantName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Dish dishToBeUpdated = merchantService.updateDish(dish, restaurantName, emailId);
            return new ResponseEntity<>(dishToBeUpdated, HttpStatus.OK);
        } catch (RestaurantNotFoundException | DishNotFoundException | MerchantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/merchant/deleteDish/{restaurantName}/{dishName}")
    public ResponseEntity<?> deleteDishFromRestaurant(@PathVariable String restaurantName, @PathVariable String dishName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            boolean dishFromRestaurant = merchantService.deleteDishFromRestaurant(restaurantName, dishName, emailId);
            return new ResponseEntity<>(dishFromRestaurant, HttpStatus.ACCEPTED);
        } catch (MerchantNotFoundException | DishNotFoundException | RestaurantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<?> getAllRestaurants() {
        try {
            List<Restaurant> allRestaurants = merchantService.getAllRestaurants();
            return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/merchant/addRestaurant")
    public ResponseEntity<?> addRestaurantToMerchant(@RequestBody Restaurant restaurant, HttpServletRequest request) throws MerchantNotFoundException, RestaurantAlreadyExistsException, RestaurantNotFoundException {
        String emailId = request.getAttribute("emailId").toString();
        try {
            responseEntity = new ResponseEntity<>(merchantService.addRestaurant(restaurant, emailId), HttpStatus.CREATED);
            return responseEntity;
        } catch (MerchantNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/merchant/getDishesBasedOnCuisine/{restaurantName}/{cuisineName}")
    public ResponseEntity<?> getDishes(@PathVariable String restaurantName, @PathVariable String cuisineName) throws CuisineNotFoundException {

        try {
            responseEntity = new ResponseEntity<>(merchantService.getAllDishesBasedOnCuisine(cuisineName, restaurantName), HttpStatus.FOUND);

        } catch (CuisineNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/getRestaurantBasedOnLocation/{restaurantLocation}")
    public ResponseEntity<?> getRestaurantOnLocation(@PathVariable String restaurantLocation) {
        try {
            responseEntity = new ResponseEntity<>(merchantService.getRestaurantBasedOnLocation(restaurantLocation), HttpStatus.FOUND);
            return responseEntity;
        } catch (RestaurantNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> fetchMerchantRestaurants(HttpServletRequest request) throws MerchantNotFoundException {
        String emailId = request.getAttribute("emailId").toString();
        try {
            responseEntity = new ResponseEntity<>(merchantService.getMerchantRestaurants(emailId), HttpStatus.FOUND);
        } catch (MerchantNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
