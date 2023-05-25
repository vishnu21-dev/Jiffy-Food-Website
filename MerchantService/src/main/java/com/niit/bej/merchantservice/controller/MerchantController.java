package com.niit.bej.merchantservice.controller;

import com.niit.bej.merchantservice.domain.Cuisine;
import com.niit.bej.merchantservice.domain.Dish;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.exception.*;
import com.niit.bej.merchantservice.service.MerchantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchantZomato")
public class MerchantController {

    private final MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/registerMerchant")
    public ResponseEntity<?> register(@RequestBody Merchant merchant) {
        try {
            Merchant registerMerchant = merchantService.register(merchant);
            return new ResponseEntity<>(registerMerchant, HttpStatus.CREATED);
        } catch (MerchantAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/merchant/addCuisines")
    public ResponseEntity<?> addCuisines(@RequestBody Cuisine cuisine, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Merchant updatedMerchant = merchantService.addCuisines(cuisine, emailId);
            return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
        } catch (MerchantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CuisineAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/merchant/getAllCuisines")
    public ResponseEntity<?> getAllCuisines(HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Cuisine> cuisineList = merchantService.getAllCuisines(emailId);
            return new ResponseEntity<>(cuisineList, HttpStatus.OK);
        } catch (MerchantNotFoundException | CuisineNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/merchant/updateCuisine")
    public ResponseEntity<?> updateCuisine(@RequestBody Cuisine cuisine, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Cuisine updatedCuisine = merchantService.updateCuisine(cuisine, emailId);
            return new ResponseEntity<>(updatedCuisine, HttpStatus.OK);
        } catch (MerchantNotFoundException | CuisineNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/merchant/addDishesToCuisine/{cuisineName}")
    public ResponseEntity<?> addDishesToCuisine(@RequestBody Dish dish, @PathVariable String cuisineName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Cuisine addDishes = merchantService.addDishesToCuisine(dish, emailId, cuisineName);
            return new ResponseEntity<>(addDishes, HttpStatus.OK);
        } catch (MerchantNotFoundException | CuisineNotFoundException e) {
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

    @GetMapping("/merchant/getAllDishesFromCuisine/{cuisineName}")
    public ResponseEntity<?> getAllDishesFromCuisine(@PathVariable String cuisineName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Dish> dishList = merchantService.getAllDishesFromACuisine(cuisineName, emailId);
            return ResponseEntity.ok(dishList);
        } catch (MerchantNotFoundException | CuisineNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/merchant/updateDish/{cuisineName}/{updatedDish}")
    public ResponseEntity<?> updateDish(@PathVariable String cuisineName, @PathVariable Dish updatedDish, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Dish dishToBeUpdated = merchantService.updateDish(cuisineName, updatedDish, emailId);
            return new ResponseEntity<>(dishToBeUpdated, HttpStatus.OK);
        } catch (CuisineNotFoundException | DishNotFoundException | MerchantNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/merchant/deleteDishFromCuisine/{cuisineName}/{dishName}")
    public ResponseEntity<?> deleteDishFromCuisine(@PathVariable String cuisineName, @PathVariable String dishName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            boolean deleteDish = merchantService.deleteDishFromCuisine(cuisineName, dishName, emailId);
            return new ResponseEntity<>(deleteDish, HttpStatus.ACCEPTED);
        } catch (MerchantNotFoundException | CuisineNotFoundException | DishNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/merchant/deleteCuisine/{cuisineName}")
    public ResponseEntity<?> deleteCuisine(@PathVariable String cuisineName, HttpServletRequest httpServletRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            boolean deleteOneCuisine = merchantService.deleteCuisine(cuisineName, emailId);
            return new ResponseEntity<>(deleteOneCuisine, HttpStatus.ACCEPTED);
        } catch (MerchantNotFoundException | CuisineNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
