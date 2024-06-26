package com.example.habit.dto.request;

import jakarta.validation.constraints.Min;

public record HistoryRequestDto(@Min(1) Long historyId, float energy, float moisture, float protein, float fat,
                                float carbohydrate, float dietaryFiber, float calcium,
                                float iron, float phosphorus, float selenium, float sodium,
                                float vitaminA, float vitaminB1, float vitaminB2, float vitaminC) {
}
