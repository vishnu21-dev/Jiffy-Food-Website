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
}
