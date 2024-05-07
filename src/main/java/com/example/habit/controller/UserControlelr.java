package com.example.habit.controller;

import com.example.habit.annotation.UserId;
import com.example.habit.dto.ResponseDto;
import com.example.habit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserControlelr {
    private final UserService userService;

    @GetMapping("/nickname")
    public ResponseDto<?> getNickname(@UserId Long userId) {
        return ResponseDto.ok(userService.getNickname(userId));
    }

}
