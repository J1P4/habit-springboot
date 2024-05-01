package com.example.habit.repository;

import com.example.habit.domain.History;
import com.example.habit.domain.User;
import com.example.habit.dto.response.FoodNutrientSumDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUser(User user);

    @Query("SELECT h FROM History h WHERE h.user = :user AND h.ateDate BETWEEN :startDate AND :endDate")
    List<History> findAllByUserAndAteDateTimeBetween(@Param("user") User user, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT CAST(SUM(h.carbohydrate) as float) as Carbohydrate, CAST(SUM(h.protein) as float) as Protein, CAST(SUM(h.fat) as float) as Fat " +
            "FROM histories h " +
            "WHERE h.user_id = :userId AND DATE_FORMAT(h.ate_date, '%Y-%m-%d') = :ateDate " +
            "GROUP BY DATE_FORMAT(h.ate_date, '%Y-%m-%d')", nativeQuery = true)
    FoodNutrientSumDto findSumNutrientByUserAndAteDate(@Param("userId") Long userId, @Param("ateDate") LocalDate ateDate);

    interface FoodNutrientSumDto {
        float getCarbohydrate();
        float getProtein();
        float getFat();
    }

    @Query(value = "SELECT h.food_id as FoodId, f.name as Name, f.detail_classification as DetailClassification FROM histories h " +
            "Inner JOIN foods f ON h.food_id = f.id " +
            "WHERE h.user_id = :userId AND DATE_FORMAT(h.ate_date, '%Y-%m-%d') = :ateDate", nativeQuery = true)
    List<FoodAIInfo> findFoodByUserAndAteDate(@Param("userId") Long userId, @Param("ateDate") LocalDate ateDate);

    interface FoodAIInfo {
        int getFoodId();

        String getName();

        String getDetailClassification();
    }
}

