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
    private String date;
    private List<Dish> dishes;
    private double totalPrice;

    public Order(String orderId, String date, List<Dish> dishes, double totalPrice) {
        this.orderId = orderId;
        this.date = date;
        this.dishes = dishes;
        this.totalPrice = totalPrice;
    }

    public void generateOrderId() {
        this.orderId = new ObjectId().toString();
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" + "orderId='" + orderId + '\'' + ", date='" + date + '\'' + ", dishes=" + dishes + ", totalPrice=" + totalPrice + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.totalPrice, totalPrice) == 0 && Objects.equals(orderId, order.orderId) && Objects.equals(date, order.date) && Objects.equals(dishes, order.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date, dishes, totalPrice);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
