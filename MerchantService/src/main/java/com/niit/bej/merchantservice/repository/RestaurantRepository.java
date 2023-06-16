package com.niit.bej.merchantservice.repository;

import com.niit.bej.merchantservice.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Restaurant findRestaurantByName(String restaurantName);
}
