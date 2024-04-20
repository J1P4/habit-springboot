package com.example.habit.service;

import com.example.habit.domain.Food;
import com.example.habit.domain.History;
import com.example.habit.domain.User;
import com.example.habit.dto.response.FoodDto;
import com.example.habit.exception.CommonException;
import com.example.habit.exception.ErrorCode;
import com.example.habit.repository.FoodRepository;
import com.example.habit.repository.HistoryRepository;
import com.example.habit.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    private final HistoryRepository historyRepository;

    public List<FoodDto> getList(String keyword, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        //keyword가 null 혹은 공백인 경우
        if (!StringUtils.hasText(keyword)) {
            return new ArrayList<>();
        } else {
            Page<Food> foodPage = foodRepository.findByNameContaining(keyword, pageable);
            List<FoodDto> foodDtoList = foodPage.stream()
                    .map(FoodDto::fromEntity)
                    .collect(Collectors.toList());

            return foodDtoList;
        }

    }

    @Transactional
    public Boolean addFood(Long foodId, Long userId) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_FOOD));
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        History history = History.builder()
                .classification(food.getClassification())
                .detailClassification(food.getDetailClassification())
                .energy(food.getEnergy())
                .moisture(food.getMoisture())
                .protein(food.getProtein())
                .fat(food.getFat())
                .carbohydrate(food.getCarbohydrate())
                .dietaryFiber(food.getDietaryFiber())
                .calcium(food.getCalcium())
                .iron(food.getIron())
                .phosphorus(food.getPhosphorus())
                .selenium(food.getSelenium())
                .sodium(food.getSodium())
                .vitaminA(food.getVitaminA())
                .vitaminB1(food.getVitaminB1())
                .vitaminB2(food.getVitaminB2())
                .vitaminC(food.getVitaminC())
                .food(food)
                .user(user)
                .build();

        historyRepository.save(history);

        return Boolean.TRUE;
    }
}
