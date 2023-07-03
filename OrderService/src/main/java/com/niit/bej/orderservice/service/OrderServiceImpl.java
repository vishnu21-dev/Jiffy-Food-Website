package com.niit.bej.orderservice.service;

import com.niit.bej.orderservice.domain.Dish;
import com.niit.bej.orderservice.domain.Order;
import com.niit.bej.orderservice.domain.Restaurant;
import com.niit.bej.orderservice.domain.User;
import com.niit.bej.orderservice.exception.*;
import com.niit.bej.orderservice.proxy.UserProxy;
import com.niit.bej.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
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
            userProxy.registerUserProxy(user);
            return orderRepository.save(user);

        } else throw new UserAlreadyExistsException("User already exists ");
    }

    @Override
    public User getUser(String userId) throws UserNotFoundException {
        Optional<User> userOptional = orderRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = orderRepository.findById(userId).get();
            return user;

        } else throw new UserNotFoundException("User not found");
    }


    @Override
    public User addOrder(List<Dish> dishes, String userId, String date, double price) throws UserNotFoundException, OrderAlreadyExistsException {
        Optional<User> optionalUser = orderRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User loggedInUser = optionalUser.get();
            List<Order> orderList = loggedInUser.getOrders();
            if (orderList == null) {
                Order orderObj = new Order();
                orderObj.generateOrderId();
                String orderId = orderObj.getOrderId();
                Order order = new Order(orderId, date, dishes, price);
                loggedInUser.setOrders(Collections.singletonList(order));
                orderRepository.save(loggedInUser);
                return loggedInUser;
            } else {
                Order orderObj = new Order();
                orderObj.generateOrderId();
                String orderId = orderObj.getOrderId();
                Order order = new Order(orderId, date, dishes, price);
                orderList.add(order);
                loggedInUser.setOrders(orderList);
                orderRepository.save(loggedInUser);
                return loggedInUser;
            }
        }
        throw new UserNotFoundException("User Not found");
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
        } else throw new UserNotFoundException("User Not found");
    }


    @Override
    public boolean deleteOrder(String orderId, String userId) throws UserNotFoundException, OrderNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Order> orderList = user.getOrders();
            Optional<Order> requestedOrder = orderList.stream().filter(f -> f.getOrderId().equals(orderId)).findFirst();
            if (requestedOrder.isPresent()) {
                orderList.remove(requestedOrder.get()); // Use requestedOrder.get() to obtain the Order object
                user.setOrders(orderList);
                orderRepository.save(user);
                return true;
            } else {
                throw new OrderNotFoundException("The requested order is not found");
            }
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public User addRestaurantToFavorites(String userId, Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyExistsException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Restaurant> userFav = user.getFavouriteRestaurant();

            if (userFav == null) {

                user.setFavouriteRestaurant(Collections.singletonList(restaurant));

                orderRepository.save(user);
                return user;
            }
            if (userFav.contains(restaurant)) {
                throw new RestaurantAlreadyExistsException("Already present ");
            } else {
                userFav.add(restaurant);
                user.setFavouriteRestaurant(userFav);
                orderRepository.save(user);
                return user;
            }
        } else {
            throw new UserNotFoundException("User does not exists!");
        }

    }

    @Override
    public List<Restaurant> getRestaurants(String userId) throws UserNotFoundException, RestaurantNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Restaurant> restaurantList = user.getFavouriteRestaurant();
            if (restaurantList.isEmpty()) {
                throw new RestaurantNotFoundException("No Restaurant Found");
            }
            return restaurantList;

        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public User addDishToFavourites(String userId, Dish dish) throws UserNotFoundException, DishAlreadyExistsException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Dish> dishList = user.getFavouriteDish();

            if (dishList == null) {

                user.setFavouriteDish(Collections.singletonList(dish));

                orderRepository.save(user);
                return user;
            }
            if (dishList.contains(dish)) {
                throw new DishAlreadyExistsException(" Dish Already present");
            } else {
                dishList.add(dish);
                user.setFavouriteDish(dishList);
                orderRepository.save(user);
                return user;
            }
        } else {
            throw new UserNotFoundException("User does not exists!");
        }
    }

    @Override
    public List<Dish> getDishFromFavourites(String userId) throws DishNotFoundException, UserNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Dish> dishList = user.getFavouriteDish();
            if (dishList.isEmpty()) {
                throw new DishNotFoundException("No dish Found");
            }
            return dishList;

        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public boolean deleteRestaurantFromFavourites(String userId, String restaurantName) throws UserNotFoundException, RestaurantNotFoundException {
        Optional<User> userOptional = orderRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            List<Restaurant> restaurantList = user.getFavouriteRestaurant();
            Optional<Restaurant> restaurantOptional = restaurantList.stream().filter(f -> f.getName().equalsIgnoreCase(restaurantName)).findAny();
            if (restaurantOptional.isPresent()) {
                restaurantList.remove(restaurantOptional.get());
                user.setFavouriteRestaurant(restaurantList);

                orderRepository.save(user);
                return true;
            } else {
                throw new RestaurantNotFoundException("Restaurant not found");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public boolean deleteDishFromFavourites(String userId, String dishName) throws UserNotFoundException, DishNotFoundException {
        Optional<User> userOptional = orderRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            List<Dish> dishList = user.getFavouriteDish();
            Optional<Dish> dishOptional = dishList.stream().filter(f -> f.getName().equalsIgnoreCase(dishName)).findAny();
            if (dishOptional.isPresent()) {
                dishList.remove(dishOptional.get());

                user.setFavouriteDish(dishList);
                orderRepository.save(user);
                return true;
            } else {
                throw new DishNotFoundException("Dish not found");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> userOptional = orderRepository.findById(user.getEmailId());
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            // Update the user fields
            userToUpdate.setEmailId(user.getEmailId());
            userToUpdate.setImageUrl(user.getImageUrl());
            userToUpdate.setName(user.getName());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setPhoneNo(user.getPhoneNo());
            userToUpdate.setCity(user.getCity());
            userToUpdate.setAddress(user.getAddress());
            userToUpdate.setOrders(user.getOrders());
            userToUpdate.setFavouriteDish(user.getFavouriteDish());
            userToUpdate.setFavouriteRestaurant(user.getFavouriteRestaurant());
            // Save the updated user
            User savedUser = orderRepository.save(userToUpdate);
            return savedUser;
        }
        // Throw an exception or return null if user is not found
        throw new UserNotFoundException("User not found");
    }


}


