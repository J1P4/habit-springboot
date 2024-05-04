package com.example.habit.dto.response;

import com.example.habit.domain.History;

public record HistoryDto(Long historyId,float energy, float protein, float fat,
                         float carbohydrate, float dietaryFiber, float calcium,
                         float iron, float phosphorus, float selenium, float sodium,
                         float vitaminA, float vitaminB1, float vitaminB2, float vitaminC,
                         float moisture, String foodName,String time) {
    public static HistoryDto fromEntity(History history) {
        return new HistoryDto(
                history.getId(),
                history.getEnergy(),
                history.getProtein(),
                history.getFat(),
                history.getCarbohydrate(),
                history.getDietaryFiber(),
                history.getCalcium(),
                history.getIron(),
                history.getPhosphorus(),
                history.getSelenium(),
                history.getSodium(),
                history.getVitaminA(),
                history.getVitaminB1(),
                history.getVitaminB2(),
                history.getVitaminC(),
                history.getMoisture(),
                history.getFood().getName(),
                history.getTime().name()
        );
    }
}
