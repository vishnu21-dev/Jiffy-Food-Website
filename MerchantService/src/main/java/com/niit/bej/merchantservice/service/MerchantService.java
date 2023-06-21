package com.niit.bej.merchantservice.service;

import com.niit.bej.merchantservice.domain.Dish;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.domain.Restaurant;
import com.niit.bej.merchantservice.exception.*;

import java.util.List;

public interface MerchantService {


    Merchant register(Merchant merchant) throws MerchantAlreadyExistsException;

    Merchant getMerchant(String merchantId) throws MerchantNotFoundException;

    Merchant addRestaurant(Restaurant restaurant, String merchantId) throws MerchantNotFoundException, RestaurantAlreadyExistsException;

    List<Restaurant> getMerchantRestaurants(String merchantId) throws MerchantNotFoundException;

    Restaurant addDishes(Dish dish, String restaurantName, String merchantId) throws MerchantNotFoundException, DishAlreadyExistsException, RestaurantNotFoundException;

    List<Dish> getAllDishesFromRestaurant(String restaurantName, String merchantId) throws MerchantNotFoundException, DishNotFoundException, RestaurantNotFoundException;

    Dish updateDish(Dish dish, String restaurantName, String merchantId) throws RestaurantNotFoundException, DishNotFoundException, MerchantNotFoundException;

    boolean deleteDishFromRestaurant(String restaurantName, String dishName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException;

    Merchant deleteRestaurant(String restaurantName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException;

    Merchant updateMerchant(String merchantId, Merchant updatedMerchant) throws MerchantNotFoundException;

    boolean deleteMerchant(String merchantId) throws MerchantNotFoundException;

    List<Dish> getAllDishes() throws DishNotFoundException;

    List<Restaurant> getAllRestaurants() throws RestaurantNotFoundException;

    List<Dish> getAllDishesBasedOnCuisine(String cuisineName, String restaurantName) throws CuisineNotFoundException;

    List<Restaurant> getRestaurantBasedOnLocation(String restaurantLocation) throws RestaurantNotFoundException, MerchantNotFoundException;


}
