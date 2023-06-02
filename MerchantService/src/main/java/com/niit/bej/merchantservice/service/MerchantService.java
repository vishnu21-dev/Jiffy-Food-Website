package com.niit.bej.merchantservice.service;

import com.niit.bej.merchantservice.domain.Cuisine;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.domain.Restaurant;
import com.niit.bej.merchantservice.exception.CuisineAlreadyExistsException;
import com.niit.bej.merchantservice.exception.MerchantAlreadyExistsException;
import com.niit.bej.merchantservice.exception.MerchantNotFoundException;

import java.util.List;

public interface MerchantService {

    Merchant register(Merchant merchant) throws MerchantAlreadyExistsException;

    Restaurant addCuisines(List<Cuisine> cuisine, Restaurant restaurantName, String merchantId) throws MerchantNotFoundException, CuisineAlreadyExistsException;

//    List<Cuisine> getAllCuisines(String merchantId) throws MerchantNotFoundException, CuisineNotFoundException;
//
//    Cuisine updateCuisine(Cuisine cuisine, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException;
//
//    Cuisine addDishesToCuisine(Dish dish, String merchantId, String cuisineName) throws MerchantNotFoundException, CuisineNotFoundException, DishAlreadyExistsException;
//
//    List<Dish> getAllDishes() throws DishNotFoundException;
//
//    List<Dish> getAllDishesFromACuisine(String cuisineName, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException, DishNotFoundException;
//
//    Dish updateDish(String cuisineName, Dish updatedDish, String merchantId) throws CuisineNotFoundException, DishNotFoundException, MerchantNotFoundException;
//
//    boolean deleteDishFromCuisine(String cuisineName, String dishName, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException, DishNotFoundException;
//
//    boolean deleteCuisine(String cuisineName, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException;
//
//    boolean deleteMerchant(String merchantId) throws MerchantNotFoundException;
//
//    Merchant updateMerchant(String merchantId, Merchant updatedMerchant) throws MerchantNotFoundException;
}
