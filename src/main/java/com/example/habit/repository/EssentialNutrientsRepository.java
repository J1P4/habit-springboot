package com.example.habit.repository;

import com.example.habit.domain.EssentialNutrients;
import com.example.habit.type.EGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EssentialNutrientsRepository extends JpaRepository<EssentialNutrients, Long> {
    Optional<EssentialNutrients> findByEGenderAndAge(EGender eGender, int age);
}
