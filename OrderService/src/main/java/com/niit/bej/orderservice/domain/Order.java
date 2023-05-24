package com.niit.bej.orderservice.domain;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(restaurants, order.restaurants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, restaurants);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restaurants=" + restaurants +
                '}';
    }
}
