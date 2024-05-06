package com.example.habit.controller;

import com.example.habit.dto.ResponseDto;
import com.example.habit.service.ExemplaryRestaurantService;
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
@RequestMapping("api/v1/restaurant")
public class ExemplaryRestaurantController {

    private final ExemplaryRestaurantService exemplaryRestaurantService;

    @GetMapping("")
    public ResponseDto<?> getList(@RequestParam String dong,
                                  @RequestParam @Valid @NotNull @Min(0) Integer pageIndex,
                                  @RequestParam @Valid @NotNull @Min(1) Integer pageSize) {
        return ResponseDto.ok(exemplaryRestaurantService.getRestaurantList(dong, pageIndex, pageSize));
    }
}
