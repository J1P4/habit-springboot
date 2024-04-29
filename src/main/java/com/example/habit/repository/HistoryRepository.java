package com.example.habit.repository;

import com.example.habit.domain.History;
import com.example.habit.domain.User;
import com.example.habit.dto.response.FoodNutrientSumDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUser(User user);

    @Query("SELECT h FROM History h WHERE h.user = :user AND h.ateDate BETWEEN :startDate AND :endDate")
    List<History> findAllByUserAndAteDateTimeBetween(User user, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(h.carbohydrate), SUM(h.protein), SUM(h.fat) FROM History h " +
            "WHERE h.user = :user AND h.ateDate = :ateDate " +
            "group by h.user, h.ateDate")
    FoodNutrientSumDto findSumNutrientByUserAndAteDate(User user, LocalDate ateDate);
}

