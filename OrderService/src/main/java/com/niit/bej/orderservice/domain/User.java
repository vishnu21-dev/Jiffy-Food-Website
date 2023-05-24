package com.niit.bej.orderservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class User {

    @MongoId
    private String emailId;
    private String password;
    private String phoneNo;
    private Address address;

}
