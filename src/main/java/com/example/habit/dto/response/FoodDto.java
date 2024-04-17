package com.example.habit.dto.response;

import com.example.habit.domain.Food;

public record FoodDto(Long id,String name,float energy,float moisture, float protein, float fat) {
    public static FoodDto fromEntity(Food food) {
        return new FoodDto(
                food.getId(),
                food.getName(),
                food.getEnergy(),
                food.getMoisture(),
                food.getProtein(),
                food.getFat()
        );
    }
}
