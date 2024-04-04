package com.example.habit.controller;


import com.example.habit.annotation.UserId;
import com.example.habit.constant.Constant;
import com.example.habit.dto.JwtTokenDto;
import com.example.habit.dto.ResponseDto;
import com.example.habit.dto.request.RegisterRequestDto;
import com.example.habit.exception.CommonException;
import com.example.habit.exception.ErrorCode;
import com.example.habit.service.AuthService;
import com.example.habit.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseDto<JwtTokenDto> register(@UserId Long userId, @RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return ResponseDto.ok(authService.register(userId, registerRequestDto));
    }

}
