package com.niit.bej.orderservice.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Document
public class Order {
    @MongoId
    private String orderId;
    private List<Dish> dishes;

    public Order() {
    }

    public Order(String orderId, List<Dish> dishes) {
        this.orderId = orderId;
        this.dishes = dishes;
    }

    public void generateOrderId() {
        this.orderId = new ObjectId().toString();
    }

    @Override
    public String toString() {
        return "Order{" + "orderId='" + orderId + '\'' + ", dishes=" + dishes + '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(dishes, order.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dishes);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
