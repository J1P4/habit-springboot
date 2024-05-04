package com.example.habit.service;

import com.example.habit.domain.History;
import com.example.habit.domain.User;
import com.example.habit.dto.request.HistoryRequestDto;
import com.example.habit.dto.response.*;
import com.example.habit.exception.CommonException;
import com.example.habit.exception.ErrorCode;
import com.example.habit.repository.HistoryRepository;
import com.example.habit.repository.UserRepository;
import com.example.habit.type.EDateRange;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    @Transactional
    public UserEssentialNutritionListDto getList(Long userId, EDateRange dateRange) {
        User user = userRepository.findByIdWithUserEssentialNutrients(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        LocalDate today = LocalDate.now();

        switch (dateRange) {
            case DAY -> {
            }
            case WEEK -> today = today.minusDays(7);
            case MONTH -> today = today.minusMonths(1);
            default -> throw new CommonException(ErrorCode.INVALID_ARGUMENT);
        }

        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        List<History> histories = historyRepository.findAllByUserAndAteDateTimeBetween(user, startOfDay, endOfDay);

        HashMap<LocalDate, List<History>> historyMap = new HashMap<>();

        for (History history : histories) {
            LocalDate date = history.getAteDate().toLocalDate();
            if (historyMap.containsKey(date)) {
                historyMap.get(date).add(history);
            } else {
                List<History> historyList = new ArrayList<>();
                historyList.add(history);
                historyMap.put(date, historyList);
            }
        }

        List<HistoriesDto> historyDtos = new ArrayList<>();
        Set<LocalDate> keySet = historyMap.keySet();
        History allTotal = History.getZeroHistory();

        for (LocalDate date : keySet) {
            List<HistoryDto> historyList = new ArrayList<>();
            History total = History.getZeroHistory();

            for (History history : historyMap.get(date)) {
                historyList.add(HistoryDto.fromEntity(history));
                total.plus(history);
                allTotal.plus(history);
            }

            historyDtos.add(new HistoriesDto(date.toString(), HistoryDto.fromEntity(total), historyList));
        }

        return new UserEssentialNutritionListDto(
                EssentialNutritionDto.fromEntity(
                        user.getUserEssentialNutrients(),
                        (float) (ChronoUnit.DAYS.between(today, LocalDate.now()) + 1)
                ),
                historyDtos,
                HistoryDto.fromEntity(allTotal)
        );
    }

    @Transactional
    public UserEssentialNutritionDto getList(Long userId, LocalDate date) {
        User user = userRepository.findByIdWithUserEssentialNutrients(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<History> histories = historyRepository.findAllByUserAndAteDateTimeBetween(user, startOfDay, endOfDay);

        List<HistoryDto> historyList = new ArrayList<>();

        History total = History.getZeroHistory();

        for (History history : histories) {
            historyList.add(HistoryDto.fromEntity(history));
            total.plus(history);
        }

        return new UserEssentialNutritionDto(
                EssentialNutritionDto.fromEntity(user.getUserEssentialNutrients()),
                new HistoriesDto(date.toString(), HistoryDto.fromEntity(total), historyList)
        );
    }

    @Transactional
    public Boolean update(Long userId, HistoryRequestDto historyRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        History history = historyRepository.findById(historyRequestDto.historyId()).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_HISTORY));

        history.update(historyRequestDto);
        historyRepository.save(history);


        return Boolean.TRUE;
    }

    @Transactional
    public HistoryDto get(Long userId, Long historyId) {
        History history = historyRepository.findById(historyId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_HISTORY));
        if (!history.getUser().getId().equals(userId)) {
            throw new CommonException(ErrorCode.NOT_FOUND_HISTORY);
        }
        return HistoryDto.fromEntity(history);
    }


}
