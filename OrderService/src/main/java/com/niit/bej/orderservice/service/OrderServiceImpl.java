package com.niit.bej.orderservice.service;

import com.niit.bej.orderservice.domain.Cuisine;
import com.niit.bej.orderservice.domain.Order;
import com.niit.bej.orderservice.domain.Restaurant;
import com.niit.bej.orderservice.domain.User;
import com.niit.bej.orderservice.exception.*;
import com.niit.bej.orderservice.proxy.UserProxy;
import com.niit.bej.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserProxy userProxy;

    public OrderServiceImpl(OrderRepository orderRepository, UserProxy userProxy) {
        this.orderRepository = orderRepository;
        this.userProxy = userProxy;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        Optional<User> userOptional = orderRepository.findById(user.getEmailId());
        if (userOptional.isEmpty()) {

            orderRepository.save(user);
            userProxy.registerUserProxy(user);
            return user;
        }

        throw new UserAlreadyExistsException(" User already exists ");
    }

    @Override
    public User addOrder(Order order, String userId) throws UserNotFoundException, OrderAlreadyExistsException {
        return null;
    }

    @Override
    public List<Order> getAllOrders(String userId) throws UserNotFoundException, OrderNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Order> orders = user.getOrders();
            if (orders.isEmpty()) {
                throw new OrderNotFoundException("There is no order present");

            }
            return orders;
        } else
            throw new UserNotFoundException("User Not found");
    }

    @Override
    public User getRestaurantByUserLocation(User user, String restaurantName) throws UserNotFoundException, RestaurantNotFoundException {
        return null;
    }

    @Override
    public Restaurant getRestaurantByPreference(Restaurant restaurant, String userId) throws UserNotFoundException, RestaurantNotFoundException {
        return null;
    }

    @Override
    public List<Cuisine> getRestaurantByCuisine(Cuisine cuisine, String restaurantName) throws CuisineNotFoundException, RestaurantNotFoundException {
        return null;
    }

    @Override
    public boolean deleteOrder(Integer orderId, String userId) throws UserNotFoundException, OrderNotFoundException {
        return false;
    }
}
