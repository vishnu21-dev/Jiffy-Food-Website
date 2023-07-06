package com.niit.bej.merchantservice.repository;


import com.niit.bej.merchantservice.domain.Cuisine;
import com.niit.bej.merchantservice.domain.Dish;
import com.niit.bej.merchantservice.domain.Merchant;
import com.niit.bej.merchantservice.domain.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class MerchantRepositoryTest {
    @Autowired
    private MerchantRepository merchantRepository;


    private Dish dish;
    private Cuisine cuisine;
    private Restaurant restaurant;
    private Merchant merchant;

    @BeforeEach
    public void setUp() {
        dish = new Dish("pizza", "FastFood", 200, "main course", "jsfhdskfdsf", "dsafadf", "Veg");
        cuisine = new Cuisine("Italian", Collections.singletonList(dish));
        restaurant = new Restaurant("Piccola", "yhfenf", Collections.singletonList(dish), "Mumbai", true);
        merchant = new Merchant("xyz@gmail.com", "Jane Doe", "jjncjn", "approved", Collections.singletonList(restaurant));

    }

    @AfterEach
    public void tearDown() {
        dish = null;
        cuisine = null;
        merchant = null;
        merchantRepository.deleteAll();
    }

    @Test
    @DisplayName("Test for saving ,merchant details into database")
    public void saveMerchantData() {
        merchantRepository.save(merchant);
        Merchant merchant1 = merchantRepository.findById(merchant.getEmailId()).get();
        assertEquals(merchant.getEmailId(), merchant1.getEmailId());
    }


//    @Test
//    @DisplayName("Test for adding cuisines")
//    public void addCuisine(){
//        merchantRepository.save(merchant);
//        Merchant addOneCuisine = merchantRepository.save(cuisine);
//        assertEquals(addOneCuisine.getCuisines(),cuisine.getName());
//
//
//
//    }


}
