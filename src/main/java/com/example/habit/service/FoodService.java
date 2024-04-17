package com.example.habit.service;

import com.example.habit.domain.Food;
import com.example.habit.dto.response.FoodDto;
import com.example.habit.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public List<FoodDto> getList(String keyword, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Food> foodPage;

        //keyword가 null 혹은 공백인 경우
        if (!StringUtils.hasText(keyword)) {
            foodPage = foodRepository.findAll(pageable);
        } else {

            foodPage = foodRepository.findByNameContaining(keyword, pageable);
        }


        List<FoodDto> foodDtoList = foodPage.stream()
                .map(FoodDto::fromEntity)
                .collect(Collectors.toList());

        return foodDtoList;
    }

}
