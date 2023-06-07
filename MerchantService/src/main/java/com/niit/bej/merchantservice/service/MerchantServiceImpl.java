package com.niit.bej.merchantservice.service;

import com.niit.bej.merchantservice.domain.Cuisine;
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
    public Restaurant addDishes(Dish dish, String merchantId) throws MerchantNotFoundException, DishAlreadyExistsException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantRepository.findById(merchantId).get();
            Restaurant restaurant = merchant.getRestaurantName();
            List<Dish> dishList = restaurant.getDishes();
            Optional<Dish> dish1 = dishList.stream().filter(f -> f.getName().equalsIgnoreCase(dish.getName())).findAny();
            if (dish1.isPresent()) {
                throw new DishAlreadyExistsException("Dish already present");
            } else {
                dishList.add(dish);
                restaurant.setDishes(dishList);
                merchantRepository.save(merchant);
                return restaurant;

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
    public Dish updateDish(String cuisineName, Dish updatedDish, String merchantId) throws CuisineNotFoundException, DishNotFoundException, MerchantNotFoundException {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            List<Cuisine> merchantCuisines = merchant.get().getRestaurantName().getCuisines();
            Optional<Cuisine> requestedCuisine = merchantCuisines.stream().filter(cuisine -> cuisine.getName().equalsIgnoreCase(cuisineName)).findFirst();

            if (requestedCuisine.isPresent()) {
                Cuisine cuisine = requestedCuisine.get();
                List<Dish> dishes = cuisine.getDishes();
                Optional<Dish> dishToUpdate = dishes.stream().filter(dish -> dish.getName().equals(updatedDish.getName())).findFirst();

                if (dishToUpdate.isPresent()) {
                    Dish existingDish = dishToUpdate.get();
                    existingDish.setName(updatedDish.getName());
                    existingDish.setCategory(updatedDish.getCategory());
                    existingDish.setPrice(updatedDish.getPrice());
                    existingDish.setImageUrl(updatedDish.getImageUrl());
                    existingDish.setDescription(updatedDish.getDescription());
                    merchantRepository.save(merchant.get());
                    return existingDish;
                } else {
                    throw new DishNotFoundException("Dish not found in the given cuisine.");
                }
            } else {
                throw new CuisineNotFoundException("Cuisine not found.");
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
