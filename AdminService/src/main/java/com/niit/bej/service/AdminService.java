package com.niit.bej.service;

import com.niit.bej.domain.Restaurant;
import com.niit.bej.exception.AdminDoesNotExistException;
import com.niit.bej.exception.MerchantAlreadyExistException;

import java.util.List;

public interface AdminService {
    Restaurant addRestaurant(Restaurant restaurant) throws MerchantAlreadyExistException;

    List<Restaurant> getRestaurant(String userId, String password) throws AdminDoesNotExistException;
}
