package com.example.habit.dto.request;

import com.example.habit.type.EGender;

public record RegisterRequestDto(int age, EGender gender,String nickname,int height,int weight) {
}
