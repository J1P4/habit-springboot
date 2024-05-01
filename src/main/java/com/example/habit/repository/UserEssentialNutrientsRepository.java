package com.example.habit.repository;

import com.example.habit.domain.UserEssentialNutrients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEssentialNutrientsRepository extends JpaRepository<UserEssentialNutrients, Long> {
}
