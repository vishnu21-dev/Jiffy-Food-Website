package com.niit.bej.merchantservice.service;

import com.niit.bej.merchantservice.domain.Cuisine;
import com.niit.bej.merchantservice.domain.Dish;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.domain.Restaurant;
import com.niit.bej.merchantservice.exception.*;

import java.util.List;

public interface MerchantService {
Merchant getMerchant(String merchantId) throws MerchantNotFoundException;

    Merchant register(Merchant merchant) throws MerchantAlreadyExistsException;

    Restaurant addCuisines(Cuisine cuisine, String restaurantName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException, CuisineAlreadyExistsException;

    List<Cuisine> getAllCuisines(String restaurantName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException, CuisineNotFoundException;

    Restaurant addDishes(Dish dish, String merchantId) throws MerchantNotFoundException, DishAlreadyExistsException;

    List<Dish> getAllDishes() throws DishNotFoundException;

    List<Dish> getAllDishesFromRestaurant(String restaurantName, String merchantId) throws MerchantNotFoundException, DishNotFoundException, RestaurantNotFoundException;

    Dish updateDish(Dish updatedDish, String merchantId) throws CuisineNotFoundException, DishNotFoundException, MerchantNotFoundException;



    boolean deleteMerchant(String merchantId) throws MerchantNotFoundException;

    Merchant updateMerchant(String merchantId, Merchant updatedMerchant) throws MerchantNotFoundException;

    List<Restaurant> getAllRestaurants() throws RestaurantNotFoundException;
}
