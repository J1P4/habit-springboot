package com.example.habit.service;

import com.example.habit.domain.History;
import com.example.habit.domain.User;
import com.example.habit.dto.request.HistoryRequestDto;
import com.example.habit.dto.response.FoodDto;
import com.example.habit.dto.response.HistoriesDto;
import com.example.habit.dto.response.HistoryDto;
import com.example.habit.exception.CommonException;
import com.example.habit.exception.ErrorCode;
import com.example.habit.repository.HistoryRepository;
import com.example.habit.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public List<HistoriesDto> getList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        List<History> histories = historyRepository.findAllByUser(user);

        HashMap<LocalDate,List<History>> historyMap = new HashMap<>();

        for(History history : histories){
            LocalDate date = history.getAteDate().toLocalDate();
            if(historyMap.containsKey(date)){
                historyMap.get(date).add(history);
            }else{
                List<History> historyList = new ArrayList<>();
                historyList.add(history);
                historyMap.put(date,historyList);
            }
        }

        List<HistoriesDto> historyDtos = new ArrayList<>();

        Set<LocalDate> keySet = historyMap.keySet();
        for(LocalDate date : keySet){
            List<HistoryDto> historyList = new ArrayList<>();
            for(History history : historyMap.get(date)){
                historyList.add(HistoryDto.fromEntity(history));
            }

            historyDtos.add(new HistoriesDto(date.toString(),historyList));
        }

        return historyDtos;
    }

    @Transactional
    public Boolean update(Long userId, HistoryRequestDto historyRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        History history = historyRepository.findById(historyRequestDto.historyId()).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_HISTORY));

        history.update(historyRequestDto);
        historyRepository.save(history);


        return Boolean.TRUE;
    }


}
