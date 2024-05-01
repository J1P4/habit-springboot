package com.example.habit.repository;

import com.example.habit.domain.Food;
import com.example.habit.dto.response.FoodsForNutrient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Page<Food> findByNameContaining(String foodName, Pageable pageable);

    @Query("SELECT f.id, f.name " +
            "FROM Food f " +
            "WHERE f.carbohydrate > :carbohydrate OR f.protein > :protein OR f.fat > :fat " +
            "ORDER BY (CASE WHEN f.carbohydrate > :carbohydrate THEN 1 ELSE 0 END + " +
            "CASE WHEN f.protein > :protein THEN 1 ELSE 0 END + " +
            "CASE WHEN f.fat > :fat THEN 1 ELSE 0 END) DESC " +
            "LIMIT 10")
    List<FoodsForNutrient> findFoodsByNutrient(@Param("carbohydrate") float carbohydrate, @Param("protein") float protein, @Param("fat") float fat);

    @Query("SELECT f.id, f.name " +
            "FROM Food f " +
            "ORDER BY RAND() " +
            "LIMIT 10")
    List<FoodsForNutrient> findFoodByRandom();
}
