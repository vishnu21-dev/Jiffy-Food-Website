package com.niit.bej.merchantservice.repository;

import com.niit.bej.merchantservice.domain.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends MongoRepository<Dish, String> {
}
