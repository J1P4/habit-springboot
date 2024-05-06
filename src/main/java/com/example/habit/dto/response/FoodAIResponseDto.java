package com.example.habit.dto.response;

public record FoodAIResponseDto(int categoryCode, String name, String category, float moisture, float carbohydrate,
                                float protein, float fat, float kcal) {
}
