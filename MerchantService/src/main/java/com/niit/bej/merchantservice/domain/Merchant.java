package com.niit.bej.merchantservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Document
public class Merchant {

    @MongoId
    private String emailId;
    private String merchantName;
    private String password;
    private String location;
    private List<Restaurant> restaurants;

    public Merchant(String emailId, String merchantName, String password, String location, List<Restaurant> restaurants) {
        this.emailId = emailId;
        this.merchantName = merchantName;
        this.password = password;
        this.location = location;
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "Merchant{" + "emailId='" + emailId + '\'' + ", merchantName='" + merchantName + '\'' + ", password='" + password + '\'' + ", location='" + location + '\'' + ", restaurants=" + restaurants + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant merchant = (Merchant) o;
        return Objects.equals(emailId, merchant.emailId) && Objects.equals(merchantName, merchant.merchantName) && Objects.equals(password, merchant.password) && Objects.equals(location, merchant.location) && Objects.equals(restaurants, merchant.restaurants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, merchantName, password, location, restaurants);
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
