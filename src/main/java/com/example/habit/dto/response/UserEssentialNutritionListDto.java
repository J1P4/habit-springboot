package com.example.habit.dto.response;

import java.util.List;

public record UserEssentialNutritionListDto(EssentialNutritionDto essentialNutritionDto, List<HistoriesDto> historiesDtoList, HistoryDto allTotal) {
}
