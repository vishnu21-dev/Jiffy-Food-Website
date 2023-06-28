package com.niit.bej.repository;

import com.niit.bej.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Restaurant, String> {
}
