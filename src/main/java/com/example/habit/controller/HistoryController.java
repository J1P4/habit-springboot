package com.example.habit.controller;

import com.example.habit.annotation.UserId;
import com.example.habit.dto.ResponseDto;
import com.example.habit.dto.request.HistoryRequestDto;
import com.example.habit.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/history")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("")
    public ResponseDto<?> getList(@UserId Long userId) {
        return ResponseDto.ok(historyService.getList(userId));
    }

    @PutMapping("")
    public ResponseDto<?> update(@UserId Long userId,@RequestBody HistoryRequestDto historyRequestDto) {
        return ResponseDto.ok(historyService.update(userId, historyRequestDto));
    }

}
