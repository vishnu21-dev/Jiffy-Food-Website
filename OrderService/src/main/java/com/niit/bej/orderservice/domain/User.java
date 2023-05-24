package com.niit.bej.orderservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Document
public class User {

    @MongoId
    private String emailId;
    private String password;
    private String phoneNo;
    private Address address;
    private List<Order> orders;

    public User(String emailId, String password, String phoneNo, Address address, List<Order> orders) {
        this.emailId = emailId;
        this.password = password;
        this.phoneNo = phoneNo;
        this.address = address;
        this.orders = orders;
    }

    public User() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailId, user.emailId) && Objects.equals(password, user.password) && Objects.equals(phoneNo, user.phoneNo) && Objects.equals(address, user.address) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password, phoneNo, address, orders);
    }

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address=" + address +
                ", orders=" + orders +
                '}';
    }
}
