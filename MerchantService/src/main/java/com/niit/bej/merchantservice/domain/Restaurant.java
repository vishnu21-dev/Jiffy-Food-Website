package com.niit.bej.merchantservice.domain;

import java.util.List;
import java.util.Objects;

public class Restaurant {

    private String name;
    private String imageUrl;
    private List<Cuisine> cuisines;
    private String location;

    public Restaurant(String name, String imageUrl, List<Cuisine> cuisines, String location) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.cuisines = cuisines;
        this.location = location;
    }

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(cuisines, that.cuisines) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl, cuisines, location);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", cuisines=" + cuisines +
                ", location='" + location + '\'' +
                '}';
    }
}
