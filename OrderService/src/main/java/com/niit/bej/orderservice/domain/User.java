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
    private List<Restaurant> restaurantList;
    private List<Dish> dishList;

    public User() {
    }

    public User(String emailId, String imageUrl, String name, String password, String phoneNo, String city, String address, List<Order> orders, List<Restaurant> restaurantList, List<Dish> dishList) {
        this.emailId = emailId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.city = city;
        this.address = address;
        this.orders = orders;
        this.restaurantList = restaurantList;
        this.dishList = dishList;
    }

    @Override
    public String toString() {
        return "User{" + "emailId='" + emailId + '\'' + ", imageUrl='" + imageUrl + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", phoneNo='" + phoneNo + '\'' + ", city='" + city + '\'' + ", address='" + address + '\'' + ", orders=" + orders + ", restaurantList=" + restaurantList + ", dishList=" + dishList + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailId, user.emailId) && Objects.equals(imageUrl, user.imageUrl) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(phoneNo, user.phoneNo) && Objects.equals(city, user.city) && Objects.equals(address, user.address) && Objects.equals(orders, user.orders) && Objects.equals(restaurantList, user.restaurantList) && Objects.equals(dishList, user.dishList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, imageUrl, name, password, phoneNo, city, address, orders, restaurantList, dishList);
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

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
