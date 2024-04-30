package com.example.habit.repository;

import com.example.habit.domain.EssentialNutrients;
import com.example.habit.type.EGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EssentialNutrientsRepository extends JpaRepository<EssentialNutrients, Long> {
    @Query("SELECT e FROM EssentialNutrients e WHERE e.eGender = :gender AND e.age = :age ")
    Optional<EssentialNutrients> findByEGenderAndAge(@Param("gender") EGender gender, @Param("age") int age);
}
