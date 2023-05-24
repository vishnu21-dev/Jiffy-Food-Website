package com.niit.bej.orderservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class User {

    @MongoId
    private String emailId;
    private String password;
    private String phoneNo;
    private Address address;
    private List<Restaurant> restaurants;
    private List<Order> orders;

    public User(String emailId, String password, String phoneNo, Address address, List<Restaurant> restaurants, List<Order> orders) {
        this.emailId = emailId;
        this.password = password;
        this.phoneNo = phoneNo;
        this.address = address;
        this.restaurants = restaurants;
        this.orders = orders;
    }

    public User() {
    }
}
