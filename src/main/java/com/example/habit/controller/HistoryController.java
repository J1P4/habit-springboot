package com.example.habit.controller;

import com.example.habit.annotation.Date;
import com.example.habit.annotation.UserId;
import com.example.habit.dto.ResponseDto;
import com.example.habit.dto.request.HistoryRequestDto;
import com.example.habit.service.HistoryService;
import com.example.habit.type.EDateRange;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/history")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("")
    public ResponseDto<?> getList(@UserId Long userId, @RequestParam(value = "dateRange")EDateRange dateRange) {
        return ResponseDto.ok(historyService.getList(userId, dateRange));
    }

    @GetMapping("/{date}")
    public ResponseDto<?> getList(@UserId Long userId, @PathVariable("date") @Date String date) {
        return ResponseDto.ok(historyService.getList(userId, LocalDate.parse(date)));
    }

    @GetMapping("/specific/{historyId}")
    public ResponseDto<?> get(@UserId Long userId, @PathVariable("historyId") Long historyId) {
        return ResponseDto.ok(historyService.get(userId, historyId));
    }


    @PatchMapping("")
    public ResponseDto<?> update(@UserId Long userId,@RequestBody @Valid HistoryRequestDto historyRequestDto) {
        return ResponseDto.ok(historyService.update(userId, historyRequestDto));
    }

}
