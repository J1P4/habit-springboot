package com.example.habit.dto.request;

import com.example.habit.type.ETime;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FoodRequestDto (@Min(1) Long foodId,@NotNull ETime time) {
}
