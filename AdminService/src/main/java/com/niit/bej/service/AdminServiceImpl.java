package com.niit.bej.service;

import com.niit.bej.domain.Restaurant;
import com.niit.bej.exception.AdminDoesNotExistException;
import com.niit.bej.exception.MerchantAlreadyExistException;
import com.niit.bej.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) throws MerchantAlreadyExistException {
        if (adminRepository.findById(restaurant.getName()).isPresent()) {
            throw new MerchantAlreadyExistException("Merchant Already Exist ");
        } else
            adminRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public List<Restaurant> getRestaurant(String userId, String password) throws AdminDoesNotExistException {
        if (userId.equals("jiffyfoodapp@gmail.com")) {
            if (password.equals("jiffyfood123")) {
                return adminRepository.findAll();
            }
            throw new AdminDoesNotExistException("Check your credentials");

        }
        throw new AdminDoesNotExistException("Check your credentials");
    }
}
