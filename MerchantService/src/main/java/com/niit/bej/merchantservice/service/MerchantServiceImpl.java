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

import java.util.ArrayList;
import java.util.Collections;
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
    public Merchant register(Merchant merchant) throws MerchantAlreadyExistsException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchant.getEmailId());
        if (merchantOptional.isEmpty()) {
            merchantProxy.registerProxy(merchant);
            return merchantRepository.save(merchant);

        } else throw new MerchantAlreadyExistsException(" Merchant already exists ");
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
    public Merchant addRestaurant(Restaurant restaurant, String merchantId) throws MerchantNotFoundException, RestaurantAlreadyExistsException {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        Merchant merchant1 = merchant.get();
        if (merchant.isEmpty()) {
            throw new MerchantNotFoundException("merchant not found");
        }
        List<Restaurant> list = new ArrayList<>();
        List<Restaurant> existingRestaurants = merchant1.getRestaurants();
        if (existingRestaurants != null) {
            for (Restaurant rl : existingRestaurants) {
                if (rl.getName().equalsIgnoreCase(restaurant.getName())) {
                    throw new RestaurantAlreadyExistsException("restaurant already exists");
                }
            }
            merchant1.getRestaurants().add(restaurant);
        } else {
            list.add(restaurant);
            merchant1.setRestaurants(list);
        }
        restaurantRepository.save(merchant1.getRestaurants().get(0));
        return merchantRepository.save(merchant1);
    }

    @Override
    public List<Restaurant> getMerchantRestaurants(String merchantId) throws MerchantNotFoundException {
        if (merchantRepository.findById(merchantId).isEmpty()) {
            throw new MerchantNotFoundException("merchant not found");
        }
        return merchantRepository.findById(merchantId).get().getRestaurants();
    }

    @Override
    public Restaurant addDishes(Dish dish, String restaurantName, String merchantId) throws MerchantNotFoundException, DishAlreadyExistsException, RestaurantNotFoundException {
        Merchant loggedInMerchant;
        if (merchantRepository.findById(merchantId).isPresent()) {
            loggedInMerchant = merchantRepository.findById(merchantId).get();
            Restaurant oneRestaurant;
            if (loggedInMerchant.getRestaurants() == null) {
                throw new RestaurantNotFoundException("Restaurant does not exists!");
            } else {
                List<Restaurant> restaurantList = loggedInMerchant.getRestaurants();
                if (restaurantList.stream().anyMatch(item -> item.getName().equalsIgnoreCase(restaurantName))) {
                    oneRestaurant = restaurantList.stream().filter(item -> item.getName().equalsIgnoreCase(restaurantName)).findAny().get();
                    List<Dish> dishList = oneRestaurant.getDishes();
                    if (dishList == null) {
                        oneRestaurant.setDishes(Collections.singletonList(dish));
                        merchantRepository.save(loggedInMerchant);
                        return oneRestaurant;
                    } else {
                        if (dishList.stream().anyMatch(filter -> filter.getName().equals(dish.getName()))) {
                            throw new DishAlreadyExistsException("Dish already exists!");
                        } else {
                            dishList.add(dish);
                            oneRestaurant.setDishes(dishList);
                            loggedInMerchant.setRestaurants(restaurantList);
                        }
                    }
                } else {
                    throw new RestaurantNotFoundException("Restaurant does not exists!");
                }
            }
            merchantRepository.save(loggedInMerchant);
            return oneRestaurant;
        } else {
            throw new MerchantNotFoundException("Merchant not found!");
        }
    }

    @Override
    public List<Dish> getAllDishesFromRestaurant(String restaurantName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException {
        Merchant loggedInMerchant;
        if (merchantRepository.findById(merchantId).isPresent()) {
            loggedInMerchant = merchantRepository.findById(merchantId).get();
            List<Restaurant> restaurantList = loggedInMerchant.getRestaurants();
            if (restaurantList == null) {
                throw new RestaurantNotFoundException("Restaurant does not exist!");
            } else {
                Optional<Restaurant> selectedRestaurant = restaurantList.stream().filter(restaurant -> restaurant.getName().equalsIgnoreCase(restaurantName)).findAny();
                if (selectedRestaurant == null) {
                    throw new DishNotFoundException("Dish not found!");
                } else {
                    return selectedRestaurant.get().getDishes();
                }
            }
        } else {
            throw new MerchantNotFoundException("Merchant not found!");
        }
    }

    @Override
    public Dish updateDish(Dish dish, String restaurantName, String merchantId) throws RestaurantNotFoundException, DishNotFoundException, MerchantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            List<Restaurant> restaurantList = merchant.getRestaurants();
            Optional<Restaurant> restaurant = restaurantList.stream().filter(f -> f.getName().equalsIgnoreCase(restaurantName)).findAny();
            List<Dish> dishes = restaurant.get().getDishes();
            Optional<Dish> dishToUpdate = dishes.stream().filter(f -> f.getName().equalsIgnoreCase(dish.getName())).findAny();
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
    public boolean deleteDishFromRestaurant(String restaurantName, String dishName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException, DishNotFoundException {
        Merchant loggedInMerchant;
        if (merchantRepository.findById(merchantId).isPresent()) {
            loggedInMerchant = merchantRepository.findById(merchantId).get();
            Restaurant oneRestaurant;
            if (loggedInMerchant.getRestaurants() == null) {
                throw new RestaurantNotFoundException("Restaurant does not exists!");
            } else {
                List<Restaurant> restaurantList = loggedInMerchant.getRestaurants();
                if (restaurantList.stream().anyMatch(item -> item.getName().equalsIgnoreCase(restaurantName))) {
                    oneRestaurant = restaurantList.stream().filter(item -> item.getName().equalsIgnoreCase(restaurantName)).findAny().get();
                    List<Dish> dishList = oneRestaurant.getDishes();
                    if (dishList.stream().anyMatch(filter -> filter.getName().equals(dishName))) {
                        Dish deleteDish = dishList.stream().filter(item -> item.getName().equalsIgnoreCase(dishName)).findAny().get();
                        dishList.remove(deleteDish);
                        oneRestaurant.setDishes(dishList);
                        loggedInMerchant.setRestaurants(restaurantList);
                        merchantRepository.save(loggedInMerchant);
                        return true;
                    } else {
                        throw new DishNotFoundException("Dish not found!");
                    }
                }
            }
            return true;
        } else {
            throw new MerchantNotFoundException("Merchant does not exist!");
        }
    }

    @Override
    public Merchant deleteRestaurant(String restaurantName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException {
        boolean restaurantIsPresent = false;
        if (merchantRepository.findById(merchantId).isEmpty()) {
            throw new MerchantNotFoundException("Merchant Not Found");
        }
        Merchant merchant = merchantRepository.findById(merchantId).get();
        List<Restaurant> restaurants = merchant.getRestaurants();
        restaurantIsPresent = restaurants.removeIf(x -> x.getName().equalsIgnoreCase(restaurantName));
        if (!restaurantIsPresent) {
            throw new RestaurantNotFoundException("Restaurant Is Not Found");
        }
        merchant.setRestaurants(restaurants);
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchant(String merchantId, Merchant updatedMerchant) throws MerchantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            merchant.setEmailId(updatedMerchant.getEmailId());
            merchant.setPassword(updatedMerchant.getPassword());
            merchant.setLocation(updatedMerchant.getLocation());
            merchant.setRestaurants(updatedMerchant.getRestaurants());

            return merchantRepository.save(merchant);
        } else throw new MerchantNotFoundException("Merchant not found!");

    }

    @Override
    public boolean deleteMerchant(String merchantId) throws MerchantNotFoundException {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            merchantRepository.delete(merchant);
            return true;
        } else {
            throw new MerchantNotFoundException("Merchant not found!");
        }
    }

    @Override
    public List<Dish> getAllDishes() throws DishNotFoundException {
        List<Dish> listOfDishes = dishRepository.findAll();
        if (listOfDishes.isEmpty()) {
            throw new DishNotFoundException("There is no dish present");
        } else return listOfDishes;
    }



    @Override
    public List<Dish> getAllDishesBasedOnCuisine(String cuisineName, String restaurantName) throws CuisineNotFoundException {
        Restaurant restaurant1 = restaurantRepository.findRestaurantByName(restaurantName);
        List<Dish> dishList = restaurant1.getDishes();
        List<Dish> cuisineList = dishList.stream().filter(f -> f.getCuisine().equalsIgnoreCase(cuisineName)).toList();
        if (cuisineList.isEmpty()) {
            throw new CuisineNotFoundException("The requested Cuisine not found");
        }
        return cuisineList;
    }

    @Override
    public List<Restaurant> getAllRestaurants() throws RestaurantNotFoundException {
        List<Restaurant> listOfRestaurants = restaurantRepository.findAll();
        if (listOfRestaurants.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant does not exists!");
        } else return listOfRestaurants;

    }

    @Override
    public List<Restaurant> getRestaurantBasedOnLocation(String restaurantLocation) throws RestaurantNotFoundException, MerchantNotFoundException {
        List<Merchant> merchantList = merchantRepository.findAll();
        List<Restaurant> restaurantList = new ArrayList<>();
        if (merchantList.isEmpty()) {
            throw new MerchantNotFoundException("No Merchant Found");
        } else
            for (Merchant merchant : merchantList) {
                List<Restaurant> restaurants = merchant.getRestaurants();
                for (Restaurant restaurant : restaurants) {
                    if (restaurant.getLocation().equals(restaurantLocation)) {
                        restaurantList.add(restaurant);
                    }
                }
            }


        if (restaurantList.isEmpty()) {
            throw new RestaurantNotFoundException("No Restaurant Found in the given location");
        }

        return restaurantList;

    }

}
