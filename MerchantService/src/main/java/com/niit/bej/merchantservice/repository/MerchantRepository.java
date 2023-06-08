package com.niit.bej.merchantservice.repository;

import com.niit.bej.merchantservice.domain.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends MongoRepository<Merchant, String> {

}
