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
    public User addOrder(Order order, String userId) throws UserNotFoundException, OrderAlreadyExistsException {
        User loggedInUser;
        if (orderRepository.findById(userId).isPresent()) {
            loggedInUser = orderRepository.findById(userId).get();
            List<Order> orderList = loggedInUser.getOrders();
            if (orderList == null) {
                loggedInUser.setOrders(Collections.singletonList(order));
            } else {
                if (orderList.stream().anyMatch(item -> item.getOrderId() == order.getOrderId())) {
                    throw new OrderAlreadyExistsException("Order already exists!");
                } else {
                    orderList.add(order);
                    loggedInUser.setOrders(orderList);
                }
            }
        } else {
            throw new UserNotFoundException("User does not exists!");
        }
        return orderRepository.save(loggedInUser);
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
    public boolean deleteDishFromOrder(String userId, String dishName, int orderId) throws OrderNotFoundException, UserNotFoundException, DishNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            Optional<Dish> requestedDish;
            User user = orderRepository.findById(userId).get();
            List<Order> orderList = user.getOrders();
            Optional<Order> requestedOrder = orderList.stream().filter(f -> f.getOrderId() == orderId).findFirst();
            if (requestedOrder.isPresent()) {
                Order order = requestedOrder.get();
                List<Dish> dishList = order.getDishes();
                if (dishList.isEmpty()) {
                    throw new DishNotFoundException("The Requested Dish is not present");
                } else
                    requestedDish = dishList.stream().filter(f -> f.getName().equals(dishName)).findAny();
                if (requestedDish.isPresent()) {
                    dishList.remove(requestedDish);
                    order.setDishes(dishList);
                    orderRepository.save(user);
                    return true;
                }
            } else throw new OrderNotFoundException("The Requested Order is not Present");
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public boolean deleteOrder(int orderId, String userId) throws UserNotFoundException, OrderNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Order> orderList = user.getOrders();
            Optional<Order> requestedOrder = orderList.stream().filter(f -> f.getOrderId() == orderId).findFirst();
            if (requestedOrder.isPresent()) {
                orderList.remove(requestedOrder);
                user.setOrders(orderList);
                orderRepository.save(user);
                return true;
            }
            throw new OrderNotFoundException("The requested order is not Found");
        }
        throw new UserNotFoundException("User Not found");
    }

    @Override
    public Restaurant addRestaurantToFavorites(String userId, Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyPresentException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Restaurant> restaurantList = user.getRestaurantList();
            if (restaurantList == null) {
                user.setRestaurantList(Collections.singletonList(restaurant));
                orderRepository.save(user);

                return restaurant;
            }
            Optional<Restaurant> findRestaurant = restaurantList.stream().filter(f -> f.getName().equalsIgnoreCase(restaurant.getName())).findAny();
            if (findRestaurant.isEmpty()) {
                restaurantList.add(restaurant);
                user.setRestaurantList(restaurantList);
                orderRepository.save(user);
                return restaurant;
            } else
                throw new RestaurantAlreadyPresentException("Restaurant Already Present");

        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public List<Restaurant> getRestaurant(String userId) throws UserNotFoundException, RestaurantNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Restaurant> restaurantList = user.getRestaurantList();
            if (restaurantList.isEmpty()) {
                throw new RestaurantNotFoundException("No Restaurant Found");
            }
            return restaurantList;

        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public Dish addDishToFavourites(String userId, Dish dish) throws UserNotFoundException, DishAlreadyPresentException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Dish> dishList = user.getDishList();
            if (dishList == null) {
                user.setDishList(Collections.singletonList(dish));
                orderRepository.save(user);

                return dish;
            }
            Optional<Dish> findDish = dishList.stream().filter(f -> f.getName().equalsIgnoreCase(dish.getName())).findAny();
            if (findDish.isEmpty()) {
                dishList.add(dish);
                user.setDishList(dishList);
                orderRepository.save(user);
                return dish;
            } else
                throw new DishAlreadyPresentException("Dish Already Present");

        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public List<Dish> getDish(String userId) throws DishNotFoundException, UserNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Dish> dishList = user.getDishList();
            if (dishList.isEmpty()) {
                throw new DishNotFoundException("No dish Found");
            }
            return dishList;

        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public boolean deleteRestaurant(String userId, String restaurant) throws UserNotFoundException, RestaurantNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Restaurant> restaurantList = user.getRestaurantList();
            Optional<Restaurant> restaurant1 = restaurantList.stream().filter(f -> f.getName().equalsIgnoreCase(restaurant)).findAny();
            if (restaurant1.isPresent()) {
                restaurantList.remove(restaurant);
                user.setRestaurantList(restaurantList);
                orderRepository.save(user);
                return true;
            } else
                throw new RestaurantNotFoundException("Restaurant not found");
        } else throw new UserNotFoundException("user not found");
    }

    @Override
    public boolean deleteDish(String userId, String dishName) throws UserNotFoundException, DishNotFoundException {
        if (orderRepository.findById(userId).isPresent()) {
            User user = orderRepository.findById(userId).get();
            List<Dish> dishList = user.getDishList();
            Optional<Dish> dish1 = dishList.stream().filter(f -> f.getName().equalsIgnoreCase(dishName)).findAny();
            if (dish1.isPresent()) {
                dishList.remove(dish1);
                user.setDishList(dishList);
                orderRepository.save(user);
                return true;
            } else
                throw new DishNotFoundException("dish not found");
        } else throw new UserNotFoundException("user not found");
    }
}


