package com.niit.bej.orderservice.domain;

import java.util.List;
import java.util.Objects;

public class Restaurant {

    private String name;
    private List<Cuisine> cuisines;
    private Address address;

    public Restaurant(String name, List<Cuisine> cuisines, Address address) {
        this.name = name;
        this.cuisines = cuisines;
        this.address = address;
    }

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(cuisines, that.cuisines) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cuisines, address);
    }
}
