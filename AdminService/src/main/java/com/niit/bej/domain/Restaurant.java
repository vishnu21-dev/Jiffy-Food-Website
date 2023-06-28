package com.niit.bej.domain;

import java.util.Objects;

public class Restaurant {
    private String name;
    private double rating;
    private String imageUrl;
    private String location;

    public Restaurant(String name, double rating, String imageUrl, String location) {
        this.name = name;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", imageUrl='" + imageUrl + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Double.compare(that.rating, rating) == 0 && Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, imageUrl, location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
