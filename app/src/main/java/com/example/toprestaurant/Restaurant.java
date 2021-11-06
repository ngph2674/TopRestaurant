package com.example.toprestaurant;

public class Restaurant {
    private final String name;
    private final String location;
    private final Double rating;

    public Restaurant(String name, String location, double rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Double getRating() {
        return rating;
    }
}
