package com.example.manfee.foodpanda;

/**
 * Created by manfee on 23/03/2018.
 */

public class Restaurant {

    private String id;
    private String imageUrl;
    private String restaurantName;
    private Long rating;
    private String address;

    public Restaurant() {
    }

    public Restaurant(String id, String imageUrl, String restaurantName, Long rating, String address) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.address = address;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
