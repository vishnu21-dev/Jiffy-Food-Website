package com.niit.bej.domain;

import java.util.Objects;

public class Merchant {
    private String emailId;
    private String merchantName;
    private String password;
    private String location;
    private Restaurant restaurant;

    public Merchant(String emailId, String merchantName, String password, String location, Restaurant restaurant) {
        this.emailId = emailId;
        this.merchantName = merchantName;
        this.password = password;
        this.location = location;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "emailId='" + emailId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant merchant = (Merchant) o;
        return Objects.equals(emailId, merchant.emailId) && Objects.equals(merchantName, merchant.merchantName) && Objects.equals(password, merchant.password) && Objects.equals(location, merchant.location) && Objects.equals(restaurant, merchant.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, merchantName, password, location, restaurant);
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
