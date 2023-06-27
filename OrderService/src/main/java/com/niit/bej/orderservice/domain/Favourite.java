package com.niit.bej.orderservice.domain;

import java.util.List;
import java.util.Objects;

public class Favourite {

    private int favouriteId;
    private List<Restaurant> restaurantList;
    private List<Dish> dishList;

    public Favourite(int favouriteId, List<Restaurant> restaurantList, List<Dish> dishList) {
        this.favouriteId = favouriteId;
        this.restaurantList = restaurantList;
        this.dishList = dishList;
    }

    public Favourite() {
    }

    public int getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favourite favourite = (Favourite) o;
        return favouriteId == favourite.favouriteId && Objects.equals(restaurantList, favourite.restaurantList) && Objects.equals(dishList, favourite.dishList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favouriteId, restaurantList, dishList);
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "favouriteId=" + favouriteId +
                ", restaurantList=" + restaurantList +
                ", dishList=" + dishList +
                '}';
    }
}
