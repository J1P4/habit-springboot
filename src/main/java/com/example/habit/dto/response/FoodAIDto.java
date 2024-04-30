package com.example.habit.dto.response;

import com.example.habit.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//public record FoodAIDto(int categoryCode, String name, String category) {
//    public static FoodAIDto fromFoodAIInfo(HistoryRepository.FoodAIInfo foodAIInfo) {
//        return new FoodAIDto(foodAIInfo.getFoodId(), foodAIInfo.getName(), foodAIInfo.getDetailClassification());
//    }
//}
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodAIDto {
    private int categoryCode;
    private String name;
    private String category;


    public static FoodAIDto fromFoodAIInfo(HistoryRepository.FoodAIInfo foodAIInfo) {
        return new FoodAIDto(foodAIInfo.getFoodId(), foodAIInfo.getName(), foodAIInfo.getDetailClassification());
    }
}