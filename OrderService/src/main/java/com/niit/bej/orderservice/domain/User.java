package com.niit.bej.orderservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    private String emailId;
    private String password;
    private String phoneNo;
    private Address address;

}
