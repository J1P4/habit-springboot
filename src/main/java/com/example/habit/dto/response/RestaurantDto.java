package com.example.habit.dto.response;

import com.example.habit.domain.ExemplaryRestaurant;

public record RestaurantDto(
        String name, String foodType, String mainFood,
        String roadNameAddress, String locationAddress,
        String dong, String designatedDate
) {

    public static RestaurantDto fromEntity(ExemplaryRestaurant exemplaryRestaurant) {
        return new RestaurantDto(
                exemplaryRestaurant.getName(),
                exemplaryRestaurant.getFoodType(),
                exemplaryRestaurant.getMainFood(),
                exemplaryRestaurant.getRoadNameAddress(),
                exemplaryRestaurant.getLocationAddress(),
                exemplaryRestaurant.getDong(),
                exemplaryRestaurant.getDesignatedDate().toString()
        );
    }
}
