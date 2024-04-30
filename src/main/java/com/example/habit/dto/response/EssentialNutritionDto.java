package com.example.habit.dto.response;

import com.example.habit.domain.UserEssentialNutrients;

public record EssentialNutritionDto (float energy, float protein, float fat,
                                    float carbohydrate, float dietaryFiber, float calcium,
                                    float iron, float phosphorus, float selenium, float sodium,
                                    float vitaminA, float vitaminB1, float vitaminB2, float vitaminC,
                                    float moisture) {
    public static EssentialNutritionDto fromEntity(UserEssentialNutrients userEssentialNutrients) {
        return new EssentialNutritionDto(
                userEssentialNutrients.getEnergy(),
                userEssentialNutrients.getProtein(),
                userEssentialNutrients.getFat(),
                userEssentialNutrients.getCarbohydrate(),
                userEssentialNutrients.getEssentialNutrients().getDietaryFiber(),
                userEssentialNutrients.getEssentialNutrients().getCalcium(),
                userEssentialNutrients.getEssentialNutrients().getIron(),
                userEssentialNutrients.getEssentialNutrients().getPhosphorus(),
                userEssentialNutrients.getEssentialNutrients().getSelenium(),
                userEssentialNutrients.getEssentialNutrients().getSodium(),
                userEssentialNutrients.getEssentialNutrients().getVitaminA(),
                userEssentialNutrients.getEssentialNutrients().getVitaminB1(),
                userEssentialNutrients.getEssentialNutrients().getVitaminB2(),
                userEssentialNutrients.getEssentialNutrients().getVitaminC(),
                userEssentialNutrients.getEssentialNutrients().getMoisture()
        );}
}
