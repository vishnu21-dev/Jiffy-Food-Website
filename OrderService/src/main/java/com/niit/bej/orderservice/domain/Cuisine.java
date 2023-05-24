package com.niit.bej.orderservice.domain;

import java.util.List;

public class Cuisine {

    private String name;
    private List<Dish> dishes;

    public Cuisine(String name, List<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public Cuisine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
