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

//    Order addDishesToOrder(Dish dish, int orderId, String userId) throws UserNotFoundException, DishAlreadyExistsException, OrderNotFoundException;

    List<Order> getAllOrders(String userId) throws UserNotFoundException, OrderNotFoundException;

    boolean deleteDishFromOrder(String userId, String dishName, int orderId) throws OrderNotFoundException, UserNotFoundException, DishNotFoundException;

    boolean deleteOrder(int orderId, String userId) throws UserNotFoundException, OrderNotFoundException;

    Restaurant addRestaurantToFavorites(String userId, Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyPresentException;

    List<Restaurant> getRestaurant(String userId) throws UserNotFoundException, RestaurantNotFoundException;

    Dish addDishToFavourites(String userId, Dish dish) throws UserNotFoundException, DishAlreadyPresentException;

    List<Dish> getDish(String userId) throws DishNotFoundException, UserNotFoundException;

    boolean deleteRestaurant(String userId, String restaurantName) throws UserNotFoundException, RestaurantNotFoundException;

    boolean deleteDish(String userId, String dish) throws UserNotFoundException, DishNotFoundException;

}
