package com.niit.bej.orderservice.domain;

import java.util.List;

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
}
