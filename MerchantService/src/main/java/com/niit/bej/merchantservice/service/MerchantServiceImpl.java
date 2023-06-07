package com.niit.bej.merchantservice.service;

import com.niit.bej.merchantservice.domain.Dish;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.domain.Restaurant;
import com.niit.bej.merchantservice.exception.*;
import com.niit.bej.merchantservice.proxy.MerchantProxy;
import com.niit.bej.merchantservice.repository.DishRepository;
import com.niit.bej.merchantservice.repository.MerchantRepository;
import com.niit.bej.merchantservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final MerchantProxy merchantProxy;


    @Autowired
    public MerchantServiceImpl(MerchantRepository merchantRepository, RestaurantRepository restaurantRepository, DishRepository dishRepository, MerchantProxy merchantProxy) {
        this.merchantRepository = merchantRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;

        this.merchantProxy = merchantProxy;
    }

    @Override
    public Merchant getMerchant(String merchantId) throws MerchantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantRepository.findById(merchantId).get();
            return merchant;

        } else throw new MerchantNotFoundException("Merchant not found");
    }

    @Override
    public Merchant register(Merchant merchant) throws MerchantAlreadyExistsException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchant.getEmailId());
        if (merchantOptional.isEmpty()) {
            merchantProxy.registerProxy(merchant);
            return merchantRepository.save(merchant);

        } else throw new MerchantAlreadyExistsException(" Merchant already exists ");
    }


    @Override
    public Restaurant addDishes(Dish dish, String restaurantName, String merchantId) throws MerchantNotFoundException, DishAlreadyExistsException, RestaurantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantRepository.findById(merchantId).get();
            Restaurant restaurant1 = merchant.getRestaurantName();
            List<Dish> dishList = restaurant1.getDishes();
            Optional<Dish> dish1 = dishList.stream().filter(f -> f.getName().equalsIgnoreCase(dish.getName())).findAny();
            if (dish1.isPresent()) {
                throw new DishAlreadyExistsException("Dish already present");
            } else {
                dishList.add(dish);
                restaurant1.setDishes(dishList);
                merchantRepository.save(merchant);
                return restaurant1;

            }

        } else {
            throw new MerchantNotFoundException("Merchant not found!");
        }
    }


    @Override
    public List<Restaurant> getAllRestaurants() throws RestaurantNotFoundException {
        List<Restaurant> listOfRestaurants = restaurantRepository.findAll();
        if (listOfRestaurants.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant does not exists!");
        } else return listOfRestaurants;
    }

    @Override
    public List<Dish> getAllDishes() throws DishNotFoundException {
        List<Dish> listOfDishes = dishRepository.findAll();
        if (listOfDishes.isEmpty()) {
            throw new DishNotFoundException("There is no dish present");
        } else return listOfDishes;
    }

    @Override
    public List<Dish> getAllDishesFromRestaurant(String RestaurantName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantRepository.findById(merchantId).get();
            List<Dish> merchantDish = merchant.getRestaurantName().getDishes();

            if (merchantDish.isEmpty()) {
                throw new DishNotFoundException("dish does not exist!");
            } else return merchantDish;
        } else throw new MerchantNotFoundException("Merchant does not exist!");
    }

    @Override
    public Dish updateDish(Dish dish, String restaurantName, String merchantId) throws RestaurantNotFoundException, DishNotFoundException, MerchantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            Restaurant restaurant = merchant.getRestaurantName();

            Optional<Dish> dishToUpdate = restaurant.getDishes().stream().filter(f -> f.getName().equalsIgnoreCase(dish.getName())).findAny();
            if (dishToUpdate.isPresent()) {
                Dish existingDish = dishToUpdate.get();
                existingDish.setName(dish.getName());
                existingDish.setCuisine(dish.getCuisine());
                existingDish.setCategory(dish.getCategory());
                existingDish.setPrice(dish.getPrice());
                existingDish.setImageUrl(dish.getImageUrl());
                existingDish.setDescription(dish.getDescription());
                merchantRepository.save(merchant);
                return existingDish;
            } else {
                throw new DishNotFoundException("Dish not found in the given cuisine.");
            }
        } else {
            throw new MerchantNotFoundException("Merchant not found.");
        }
    }


    @Override
    public boolean deleteMerchant(String merchantId) throws MerchantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            merchantRepository.delete(merchant);
            return true;
        } else {
            throw new MerchantNotFoundException("Merchant not found.");
        }
    }

    @Override
    public Merchant updateMerchant(String merchantId, Merchant updatedMerchant) throws MerchantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            merchant.setEmailId(updatedMerchant.getEmailId());
            merchant.setPassword(updatedMerchant.getPassword());
            merchant.setLocation(updatedMerchant.getLocation());
            merchant.setRestaurantName(updatedMerchant.getRestaurantName());


            return merchantRepository.save(merchant);
        } else throw new MerchantNotFoundException("Merchant not found.");

    }


}
