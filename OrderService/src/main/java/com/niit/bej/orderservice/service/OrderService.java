package com.niit.bej.orderservice.service;

import com.niit.bej.orderservice.domain.Dish;
import com.niit.bej.orderservice.domain.Order;
import com.niit.bej.orderservice.domain.Restaurant;
import com.niit.bej.orderservice.domain.User;
import com.niit.bej.orderservice.exception.*;

import java.util.List;

public interface OrderService {

    User registerUser(User user) throws UserAlreadyExistsException;

    User getUser(String userId) throws UserNotFoundException;

    User addOrder(Order order, String userId) throws UserNotFoundException, OrderAlreadyExistsException;


    List<Order> getAllOrders(String userId) throws UserNotFoundException, OrderNotFoundException;


    boolean deleteOrder(String orderId, String userId) throws UserNotFoundException, OrderNotFoundException;

    User addRestaurantToFavorites(String userId, Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyExistsException;

    List<Restaurant> getRestaurants(String userId) throws UserNotFoundException, RestaurantNotFoundException;

    User addDishToFavourites(String userId, Dish dish) throws UserNotFoundException, DishAlreadyExistsException;

    List<Dish> getDishFromFavourites(String userId) throws DishNotFoundException, UserNotFoundException;

    boolean deleteRestaurantFromFavourites(String userId, String restaurantName) throws UserNotFoundException, RestaurantNotFoundException;

    boolean deleteDishFromFavourites(String userId, String dish) throws UserNotFoundException, DishNotFoundException;

    User updateUser(User updatedUser) throws UserNotFoundException;

}
