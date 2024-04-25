package com.example.habit.dto.response;

import java.util.List;

public record HistoriesDto(String date,HistoryDto total, List<HistoryDto> foods) {
}
