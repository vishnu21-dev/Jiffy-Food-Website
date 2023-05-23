package com.niit.bej.merchantservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Document
public class Merchant {

    @MongoId
    private String emailId;
    private String password;
    private String location;
    private String restaurantName;
    private List<Cuisine> cuisines;

    public Merchant(String emailId, String password, String location, String restaurantName, List<Cuisine> cuisines) {
        this.emailId = emailId;
        this.password = password;
        this.location = location;
        this.restaurantName = restaurantName;
        this.cuisines = cuisines;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant merchant = (Merchant) o;
        return Objects.equals(emailId, merchant.emailId) && Objects.equals(password, merchant.password) && Objects.equals(location, merchant.location) && Objects.equals(restaurantName, merchant.restaurantName) && Objects.equals(cuisines, merchant.cuisines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password, location, restaurantName, cuisines);
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", cuisines=" + cuisines +
                '}';
    }

}
