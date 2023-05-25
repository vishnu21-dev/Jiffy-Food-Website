package com.niit.bej.orderservice.service;

import com.niit.bej.orderservice.domain.Cuisine;
import com.niit.bej.orderservice.domain.Order;
import com.niit.bej.orderservice.domain.Restaurant;
import com.niit.bej.orderservice.domain.User;
import com.niit.bej.orderservice.exception.*;

import java.util.List;

public interface OrderService {

    User registerUser(User user) throws UserAlreadyExistsException;

    User addOrder(Order order, String userId) throws UserNotFoundException, OrderAlreadyExistsException;

    List<Order> getAllOrders(String userId) throws UserNotFoundException, OrderNotFoundException;

    User getRestaurantByUserLocation(User user, String restaurantName) throws UserNotFoundException, RestaurantNotFoundException;

    Restaurant getRestaurantByPreference(Restaurant restaurant, String userId) throws UserNotFoundException, RestaurantNotFoundException;

    List<Cuisine> getRestaurantByCuisine(Cuisine cuisine, String restaurantName) throws CuisineNotFoundException, RestaurantNotFoundException;

    boolean deleteOrder(Integer orderId, String userId) throws UserNotFoundException, OrderNotFoundException;
}
