package com.example.habit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JwtTokenDto{
    @NotBlank
    private String accessToken;

    public JwtTokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
    }
}
