package com.example.habit.controller;

import com.example.habit.dto.ResponseDto;
import com.example.habit.service.FoodService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
