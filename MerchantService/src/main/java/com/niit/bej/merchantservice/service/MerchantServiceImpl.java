package com.niit.bej.merchantservice.service;

import com.niit.bej.merchantservice.domain.Cuisine;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.domain.Restaurant;
import com.niit.bej.merchantservice.exception.CuisineAlreadyExistsException;
import com.niit.bej.merchantservice.exception.MerchantAlreadyExistsException;
import com.niit.bej.merchantservice.exception.MerchantNotFoundException;
import com.niit.bej.merchantservice.exception.RestaurantNotFoundException;
import com.niit.bej.merchantservice.proxy.MerchantProxy;
import com.niit.bej.merchantservice.repository.DishRepository;
import com.niit.bej.merchantservice.repository.MerchantRepository;
import com.niit.bej.merchantservice.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        } else
            throw new MerchantAlreadyExistsException(" Merchant already exists ");
    }


    @Override
    public Restaurant addCuisines(Cuisine cuisine, String restaurantName, String merchantId) throws MerchantNotFoundException, RestaurantNotFoundException, CuisineAlreadyExistsException {
        // Check if the merchant exists
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant == null) {
            throw new MerchantNotFoundException("Merchant not found with ID: " + merchantId);
        }

        // Check if the restaurant exists
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantName);
        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant not found with name: " + restaurantName);
        }

        // Check if the cuisine already exists in the restaurant
        if (restaurant.get().getCuisines().contains(cuisine)) {
            throw new CuisineAlreadyExistsException("Cuisine already exists in the restaurant.");
        }

        // Add the cuisine to the restaurant
        Restaurant restaurant1 = restaurantRepository.findById(merchantId).get();
        restaurant1.getCuisines().add(cuisine);

        return restaurant1;
    }
//    // Helper method to find a merchant by ID
//    private Merchant findMerchantById(String merchantId) {
//        // Implementation to find and return the merchant object based on the merchantId
//        // You can replace this with your own logic or call a service/repository
//        // Example:
//        // return merchantService.findMerchantById(merchantId);
//        return null;
//    }
//
//
//    @Override
//    public List<Cuisine> getAllCuisines(String merchantId) throws MerchantNotFoundException, CuisineNotFoundException {
//        Optional<Merchant> existingMerchant = merchantRepository.findById(merchantId);
//        List<Cuisine> existingMerchantCuisineList = existingMerchant.get().getCuisines();
//        if (existingMerchantCuisineList == null) {
//            throw new CuisineNotFoundException("Cuisine does not exists!");
//        } else {
//            return existingMerchantCuisineList;
//        }
//    }
//
//    @Override
//    public Cuisine updateCuisine(Cuisine cuisine, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException {
//        if (merchantRepository.findById(merchantId).isPresent()) {
//            Merchant merchant = merchantRepository.findById(merchantId).get();
//            List<Cuisine> cuisineList = merchant.getCuisines();
//            Optional<Cuisine> existingCuisine = cuisineList.stream().filter(c -> c.getName().equals(cuisine.getName())).findFirst();
//            if (existingCuisine.isPresent()) {
//                Cuisine updatedCuisine = existingCuisine.get();
//                updatedCuisine.setName(cuisine.getName());
//                merchantRepository.save(merchant);
//                return updatedCuisine;
//            } else {
//                throw new CuisineNotFoundException("Cuisine not found.");
//            }
//        } else throw new MerchantNotFoundException("Merchant Not found ");
//    }
//
//    @Override
//    public Cuisine addDishesToCuisine(Dish dish, String merchantId, String cuisineName) throws MerchantNotFoundException, CuisineNotFoundException, DishAlreadyExistsException {
//        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
//        if (merchant.isPresent()) {
//            List<Cuisine> merchantCuisines = merchant.get().getCuisines();
//            Optional<Cuisine> requestedCuisine = merchantCuisines.stream()
//                    .filter(cuisine -> cuisine.getName().equalsIgnoreCase(cuisineName))
//                    .findFirst();
//
//            if (requestedCuisine.isPresent()) {
//                Cuisine cuisine = requestedCuisine.get();
//                List<Dish> existingDishes = cuisine.getDishes();
//                existingDishes.add(dish);
//                cuisine.setDishes(existingDishes);
//                merchantRepository.save(merchant.get());
//
//                return cuisine;
//            } else {
//                throw new CuisineNotFoundException("Cuisine not found!");
//            }
//        } else {
//            throw new MerchantNotFoundException("Merchant not found!");
//        }
//    }
//
//    @Override
//    public List<Dish> getAllDishes() throws DishNotFoundException {
//        List<Dish> listOfDishes = dishRepository.findAll();
//        if (listOfDishes.isEmpty()) {
//            throw new DishNotFoundException("There is not dish present");
//        } else return listOfDishes;
//    }
//
//    @Override
//    public List<Dish> getAllDishesFromACuisine(String cuisineName, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException, DishNotFoundException {
//        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
//        if (merchant.isPresent()) {
//            List<Cuisine> merchantCuisine = merchant.get().getCuisines();
//            Optional<Cuisine> requestedCuisine = merchantCuisine.stream().filter(p -> p.getName().equalsIgnoreCase(cuisineName)).findFirst();
//            if (requestedCuisine.isPresent()) {
//                List<Dish> dishList = requestedCuisine.get().getDishes();
//                if (dishList.isEmpty()) {
//                    throw new DishNotFoundException("dish does not exist!");
//                } else return dishList;
//            } else throw new CuisineNotFoundException("Cuisine not found");
//
//        } else throw new MerchantNotFoundException("Merchant does not exist!");
//    }
//
//    @Override
//    public Dish updateDish(String cuisineName, Dish updatedDish, String merchantId) throws CuisineNotFoundException, DishNotFoundException, MerchantNotFoundException {
//        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
//        if (merchant.isPresent()) {
//            List<Cuisine> merchantCuisines = merchant.get().getCuisines();
//            Optional<Cuisine> requestedCuisine = merchantCuisines.stream()
//                    .filter(cuisine -> cuisine.getName().equalsIgnoreCase(cuisineName))
//                    .findFirst();
//
//            if (requestedCuisine.isPresent()) {
//                Cuisine cuisine = requestedCuisine.get();
//                List<Dish> dishes = cuisine.getDishes();
//                Optional<Dish> dishToUpdate = dishes.stream()
//                        .filter(dish -> dish.getName().equals(updatedDish.getName()))
//                        .findFirst();
//
//                if (dishToUpdate.isPresent()) {
//                    Dish existingDish = dishToUpdate.get();
//                    existingDish.setName(updatedDish.getName());
//                    existingDish.setCategory(updatedDish.getCategory());
//                    existingDish.setPrice(updatedDish.getPrice());
//                    existingDish.setImageUrl(updatedDish.getImageUrl());
//                    existingDish.setDescription(updatedDish.getDescription());
//                    merchantRepository.save(merchant.get());
//                    return existingDish;
//                } else {
//                    throw new DishNotFoundException("Dish not found in the given cuisine.");
//                }
//            } else {
//                throw new CuisineNotFoundException("Cuisine not found.");
//            }
//        } else {
//            throw new MerchantNotFoundException("Merchant not found.");
//        }
//    }
//
//    @Override
//    public boolean deleteDishFromCuisine(String cuisineName, String dishName, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException, DishNotFoundException {
//        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
//        if (merchant.isPresent()) {
//            List<Cuisine> merchantCuisines = merchant.get().getCuisines();
//            Optional<Cuisine> requestedCuisine = merchantCuisines.stream()
//                    .filter(cuisine -> cuisine.getName().equalsIgnoreCase(cuisineName))
//                    .findFirst();
//
//            if (requestedCuisine.isPresent()) {
//                List<Dish> dishes = requestedCuisine.get().getDishes();
//                Optional<Dish> dishToDelete = dishes.stream()
//                        .filter(dish -> dish.getName().equalsIgnoreCase(dishName))
//                        .findFirst();
//
//                if (dishToDelete.isPresent()) {
//                    dishes.remove(dishToDelete.get());
//                    merchantRepository.save(merchant.get());
//                    return true;
//                } else {
//                    throw new DishNotFoundException("Dish not found in the given cuisine.");
//                }
//            } else {
//                throw new CuisineNotFoundException("Cuisine not found.");
//            }
//        } else {
//            throw new MerchantNotFoundException("Merchant not found.");
//        }
//    }
//
//    @Override
//    public boolean deleteCuisine(String cuisineName, String merchantId) throws MerchantNotFoundException, CuisineNotFoundException {
//        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
//        if (merchant.isPresent()) {
//            List<Cuisine> merchantCuisine = merchant.get().getCuisines();
//            Optional<Cuisine> requestedCuisine = merchantCuisine.stream().filter(p -> p.getName().equalsIgnoreCase(cuisineName)).findFirst();
//            if (requestedCuisine.isPresent()) {
//                Merchant merchant1 = merchantRepository.findById(merchantId).get();
//                merchantCuisine.remove(requestedCuisine);
//                merchant1.setCuisines(merchantCuisine);
//                return true;
//            } else throw new CuisineNotFoundException("Cuisine not found!");
//        } else throw new MerchantNotFoundException("Merchant does not exist!");
//    }
//
//    @Override
//    public boolean deleteMerchant(String merchantId) throws MerchantNotFoundException {
//        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
//        if (merchantOptional.isPresent()) {
//            Merchant merchant = merchantOptional.get();
//            merchantRepository.delete(merchant);
//            return true;
//        } else {
//            throw new MerchantNotFoundException("Merchant not found.");
//        }
//    }
//
//    @Override
//    public Merchant updateMerchant(String merchantId, Merchant updatedMerchant) throws MerchantNotFoundException {
//        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
//        if (merchantOptional.isPresent()) {
//            Merchant merchant = merchantOptional.get();
//            merchant.setEmailId(updatedMerchant.getEmailId());
//            merchant.setPassword(updatedMerchant.getPassword());
//            merchant.setLocation(updatedMerchant.getLocation());
//            merchant.setRestaurantName(updatedMerchant.getRestaurantName());
//
//            List<Cuisine> updatedCuisines = updatedMerchant.getCuisines();
//            if (updatedCuisines != null) {
//                merchant.getCuisines().clear(); // Clear existing cuisines
//
//                for (Cuisine updatedCuisine : updatedCuisines) {
//                    Cuisine existingCuisine = new Cuisine();
//                    existingCuisine.setName(updatedCuisine.getName());
//
//
//                    List<Dish> updatedDishes = updatedCuisine.getDishes();
//                    if (updatedDishes != null) {
//                        existingCuisine.getDishes().clear(); // Clear existing dishes
//
//                        for (Dish updatedDish : updatedDishes) {
//                            Dish existingDish = new Dish();
//                            existingDish.setName(updatedDish.getName());
//                            existingDish.setCategory(updatedDish.getCategory());
//                            existingDish.setPrice(updatedDish.getPrice());
//                            existingDish.setImageUrl(updatedDish.getImageUrl());
//                            existingDish.setDescription(updatedDish.getDescription());
//
//                            existingCuisine.getDishes().add(existingDish);
//                        }
//                    }
//
//                    merchant.getCuisines().add(existingCuisine);
//                }
//            }
//
//            return merchantRepository.save(merchant);
//        } else {
//            throw new MerchantNotFoundException("Merchant not found.");
//        }
//    }


}
