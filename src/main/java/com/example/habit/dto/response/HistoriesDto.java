package com.example.habit.dto.response;

import java.util.List;

public record HistoriesDto(String date, List<HistoryDto> foods) {
}
