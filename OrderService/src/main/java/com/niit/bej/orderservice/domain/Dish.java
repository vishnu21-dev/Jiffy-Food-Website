package com.niit.bej.orderservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Document

public class Dish {
    @MongoId

    private String name;
    private int price;
    private String cuisine;
    private String category;
    private String imageUrl;
    private String description;

    public Dish(String name, int price, String cuisine, String category, String imageUrl, String description) {
        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{" + "name='" + name + '\'' + ", price=" + price + ", cuisine='" + cuisine + '\'' + ", category='" + category + '\'' + ", imageUrl='" + imageUrl + '\'' + ", description='" + description + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return price == dish.price && Objects.equals(name, dish.name) && Objects.equals(cuisine, dish.cuisine) && Objects.equals(category, dish.category) && Objects.equals(imageUrl, dish.imageUrl) && Objects.equals(description, dish.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, cuisine, category, imageUrl, description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
