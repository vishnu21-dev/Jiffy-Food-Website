package com.niit.bej.orderservice.repository;

import com.niit.bej.orderservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<User, String> {


}
