package com.niit.bej.orderservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Document
public class User {

    @MongoId
    private String emailId;
    private String imageUrl;
    private String name;
    private String password;
    private String phoneNo;
    private String city;
    private String address;
    private List<Order> orders;
    private List<Dish> favouriteDish;
    private List<Restaurant> favouriteRestaurant;

    public User(String emailId, String imageUrl, String name, String password, String phoneNo, String city, String address, List<Order> orders, List<Dish> favouriteDish, List<Restaurant> favouriteRestaurant) {
        this.emailId = emailId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.city = city;
        this.address = address;
        this.orders = orders;
        this.favouriteDish = favouriteDish;
        this.favouriteRestaurant = favouriteRestaurant;
    }

    @Override
    public String toString() {
        return "User{" + "emailId='" + emailId + '\'' + ", imageUrl='" + imageUrl + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", phoneNo='" + phoneNo + '\'' + ", city='" + city + '\'' + ", address='" + address + '\'' + ", orders=" + orders + ", favouriteDish=" + favouriteDish + ", favouriteRestaurant=" + favouriteRestaurant + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailId, user.emailId) && Objects.equals(imageUrl, user.imageUrl) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(phoneNo, user.phoneNo) && Objects.equals(city, user.city) && Objects.equals(address, user.address) && Objects.equals(orders, user.orders) && Objects.equals(favouriteDish, user.favouriteDish) && Objects.equals(favouriteRestaurant, user.favouriteRestaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, imageUrl, name, password, phoneNo, city, address, orders, favouriteDish, favouriteRestaurant);
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Dish> getFavouriteDish() {
        return favouriteDish;
    }

    public void setFavouriteDish(List<Dish> favouriteDish) {
        this.favouriteDish = favouriteDish;
    }

    public List<Restaurant> getFavouriteRestaurant() {
        return favouriteRestaurant;
    }

    public void setFavouriteRestaurant(List<Restaurant> favouriteRestaurant) {
        this.favouriteRestaurant = favouriteRestaurant;
    }
}
