package com.niit.bej.orderservice.domain;

import java.util.List;

public class Order {

    private int orderId;
    private List<Restaurant> restaurants;

    public Order(int orderId, List<Restaurant> restaurants) {
        this.orderId = orderId;
        this.restaurants = restaurants;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
