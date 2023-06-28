package com.niit.bej.domain;

import java.util.List;
import java.util.Objects;

public class Admin {
    private String emailId;

    private String password;
    private List<Restaurant> restaurantList;

    public Admin() {
    }

    public Admin(String emailId, String password, List<Restaurant> restaurantList) {
        this.emailId = emailId;
        this.password = password;
        this.restaurantList = restaurantList;
    }

    @Override
    public String toString() {
        return "Admin{" + "userId='" + emailId + '\'' + ", password='" + password + '\'' + ", restaurantList=" + restaurantList + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(emailId, admin.emailId) && Objects.equals(password, admin.password) && Objects.equals(restaurantList, admin.restaurantList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password, restaurantList);
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

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
