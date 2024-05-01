package com.example.habit.service;

import com.example.habit.domain.Food;
import com.example.habit.domain.History;
import com.example.habit.domain.User;
import com.example.habit.dto.response.FoodAIDto;
import com.example.habit.dto.response.FoodDto;
import com.example.habit.dto.response.FoodNutrientSumDto;
import com.example.habit.dto.response.FoodsForNutrient;
import com.example.habit.exception.CommonException;
import com.example.habit.exception.ErrorCode;
import com.example.habit.repository.FoodRepository;
import com.example.habit.repository.HistoryRepository;
import com.example.habit.repository.UserRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class FoodService {
    @Value("http://localhost:8000/recommend_food/")
    private String ML_RECOMMENDER_URL;

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private static final RestTemplate restTemplate = new RestTemplate();

    public List<FoodDto> getList(String keyword, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        //keyword가 null 혹은 공백인 경우
        if (!StringUtils.hasText(keyword)) {
            return new ArrayList<>();
        } else {
            Page<Food> foodPage = foodRepository.findByNameContaining(keyword, pageable);

            return foodPage.stream()
                    .map(FoodDto::fromEntity)
                    .collect(Collectors.toList());
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

    //부족한 영양분에 맞는 음식 리스트
    @Transactional
    public Map<String, Object> getFoodListWithNutrient(Long userId) {
        User user = userRepository.findByIdWithUserEssentialNutrients(userId)
                .orElseThrow(() ->
                        new CommonException(ErrorCode.NOT_FOUND_USER));
        LocalDate now = LocalDate.now();

        FoodNutrientSumDto sumOfNutrient = historyRepository.findSumNutrientByUserAndAteDate(user, now);

        List<FoodsForNutrient> foodList = null;

        FoodNutrientSumDto subtractNutrition = new FoodNutrientSumDto(
                user.getUserEssentialNutrients().getCarbohydrate() - sumOfNutrient.carbohydrate(),
                user.getUserEssentialNutrients().getProtein() - sumOfNutrient.protein(),
                user.getUserEssentialNutrients().getFat() - sumOfNutrient.fat());

        if (sumOfNutrient.carbohydrate() + sumOfNutrient.protein() + sumOfNutrient.fat() > 0) {
            foodList = foodRepository.findFoodsByNutrient(subtractNutrition.carbohydrate(), subtractNutrition.protein(), subtractNutrition.fat());
        } if (foodList == null || foodList.isEmpty()) {
            foodList = foodRepository.findFoodByRandom();
        }

        Map<String, Object> result = new HashMap<>();

        result.put("deficientNutrient", subtractNutrition);
        result.put("foodList", foodList);

        return result;
    }

    /// AI 이용한 음식 추천
    @Transactional
    public Map<String,List<FoodAIDto>> getRecommendFoodList(Long userId) {
        User user = userRepository.findByIdWithUserEssentialNutrients(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        LocalDate now = LocalDate.now();

        /*** 사용자가 오늘 먹은 음식 리스트 */
        List<HistoryRepository.FoodAIInfo> userlogsforAI = historyRepository.findFoodByUserAndAteDate(user.getId(), now);

        //로그 없는 경우 해야함
        if (userlogsforAI.isEmpty())
            throw new CommonException(ErrorCode.NOT_FOUND_HISTORY);

        List<FoodAIDto> userlogs = userlogsforAI.stream()
                .map(FoodAIDto::fromFoodAIInfo)
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject body = new JSONObject();
        body.put("userlogs", userlogs);


        HttpEntity<?> request = new HttpEntity<String>(body.toJSONString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                ML_RECOMMENDER_URL,
                HttpMethod.POST,
                request,
                String.class
        );

        JsonArray foods = (JsonArray) JsonParser.parseString(response.getBody()).getAsJsonObject().get("foodlist");

        List<FoodAIDto> foodList = new ArrayList<>();
        for (JsonElement foodElement : foods) {
            Long foodId = foodElement.getAsJsonObject().get("foodId").getAsLong();
            String name = foodElement.getAsJsonObject().get("name").getAsString();
            String category = foodElement.getAsJsonObject().get("category").getAsString();
            foodList.add(new FoodAIDto(foodId.intValue(), name, category));
        }
        Map<String,List<FoodAIDto>> map = new HashMap<>();
        map.put("foodlist",foodList);

        return map;
    }

}
