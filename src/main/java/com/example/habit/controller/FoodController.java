package com.example.habit.controller;

import com.example.habit.annotation.UserId;
import com.example.habit.dto.ResponseDto;
import com.example.habit.dto.request.FoodRequestDto;
import com.example.habit.dto.response.FoodDto;
import com.example.habit.service.FoodService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("")
    public ResponseDto<?> getList(@RequestParam String keyword,
                                  @RequestParam @Valid @NotNull @Min(0) Integer pageIndex,
                                  @RequestParam @Valid @NotNull @Min(1) Integer pageSize) {
        return ResponseDto.ok(foodService.getList(keyword, pageIndex, pageSize));
    }

    @PostMapping("")
    public ResponseDto<?> addFood(@UserId Long userId, @RequestBody @Valid FoodRequestDto foodRequestDto) {
        return ResponseDto.ok(foodService.addFood(foodRequestDto.foodId(), userId, foodRequestDto.time()));
    }

    //
    @GetMapping("/recommend/nutrient")
    public ResponseDto<?> getRecommendFoodList(@UserId Long userId) {
        return ResponseDto.ok(foodService.getFoodListWithNutrient(userId));
    }

//    @GetMapping("/recommend/ai")
//    public ResponseDto<?> getRecommendFoodList(@UserId Long userId) {
//        return ResponseDto.ok(foodService.getRecommendFoodList(userId));
//    }




}
