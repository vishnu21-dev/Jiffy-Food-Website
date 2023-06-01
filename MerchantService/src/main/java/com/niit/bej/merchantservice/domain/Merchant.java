package com.niit.bej.merchantservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

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

    public Merchant() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Restaurant getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(Restaurant restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant merchant = (Merchant) o;
        return Objects.equals(emailId, merchant.emailId) && Objects.equals(password, merchant.password) && Objects.equals(location, merchant.location) && Objects.equals(restaurantName, merchant.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password, location, restaurantName);
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", restaurantName=" + restaurantName +
                '}';
    }
}
