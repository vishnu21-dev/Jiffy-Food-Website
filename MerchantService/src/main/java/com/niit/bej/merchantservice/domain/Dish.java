package com.niit.bej.merchantservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Dish {

    private String name;
    private String cuisine;
    private int price;
    private String category;
    private String imageUrl;
    private String description;

    public Dish(String name, String cuisine, int price, String category, String imageUrl, String description) {
        this.name = name;
        this.cuisine = cuisine;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return price == dish.price && Objects.equals(name, dish.name) && Objects.equals(cuisine, dish.cuisine) && Objects.equals(category, dish.category) && Objects.equals(imageUrl, dish.imageUrl) && Objects.equals(description, dish.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cuisine, price, category, imageUrl, description);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
