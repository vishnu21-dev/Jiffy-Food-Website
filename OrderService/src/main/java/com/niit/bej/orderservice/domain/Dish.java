package com.niit.bej.orderservice.domain;

public class Dish {

    private String name;
    private int price;
    private String category;
    private String imageUrl;
    private String description;

    public Dish(String name, int price, String category, String imageUrl, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Dish() {
    }
}
