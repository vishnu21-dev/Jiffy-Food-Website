package com.niit.bej.merchantservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Merchant {

    @MongoId
    private String emailId;
    private String password;
    private String location;
    private Restaurant restaurantName;

    public Merchant(String emailId, String password, String location, Restaurant restaurantName) {
        this.emailId = emailId;
        this.password = password;
        this.location = location;
        this.restaurantName = restaurantName;
    }
}
