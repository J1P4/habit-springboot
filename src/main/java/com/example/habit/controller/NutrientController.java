package com.example.habit.controller;

import com.example.habit.annotation.UserId;
import com.example.habit.dto.ResponseDto;
import com.example.habit.dto.request.RegisterRequestDto;
import com.example.habit.service.EssentialNutrientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/nutri")
public class NutrientController {

    private final EssentialNutrientService essentialNutrientService;

    @PutMapping("/update")
    public ResponseDto<Boolean> updateNutri(@UserId Long userId, @RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return ResponseDto.ok(essentialNutrientService.addUserEssentialNutrient(userId, registerRequestDto));
    }

}
