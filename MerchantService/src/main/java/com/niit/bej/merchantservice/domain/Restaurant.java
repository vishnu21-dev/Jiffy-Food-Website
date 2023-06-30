package com.niit.bej.merchantservice.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;


public class Restaurant {

    @MongoId
    private String name;
    private String imageUrl;
    private List<Dish> dishes;
    private String location;
    private Boolean status;

    public Restaurant(String name, String imageUrl, List<Dish> dishes, String location, Boolean status) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.dishes = dishes;
        this.location = location;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "name='" + name + '\'' + ", imageUrl='" + imageUrl + '\'' + ", dishes=" + dishes + ", location='" + location + '\'' + ", status=" + status + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(dishes, that.dishes) && Objects.equals(location, that.location) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl, dishes, location, status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
