package com.niit.bej.merchantservice.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;


public class Cuisine {
    @MongoId
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuisine cuisine = (Cuisine) o;
        return Objects.equals(name, cuisine.name) && Objects.equals(dishes, cuisine.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dishes);
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
