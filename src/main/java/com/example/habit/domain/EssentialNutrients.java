package com.example.habit.domain;

import com.example.habit.type.EGender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "essential_nutrients")
public class EssentialNutrients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "gender")
    private EGender gender;

    @Column(name = "age")
    private int age;

    @Column(name = "nutrient")
    private String nutrient;

    @Column(name = "amount")
    private int amount;
}
