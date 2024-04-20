package com.example.habit.domain;

import com.example.habit.dto.request.HistoryRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "histories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "classification", nullable = false)
    private String classification;

    @Column(name = "DetailClassification")
    private String detailClassification;

    @Column(name = "energy")
    private float energy = 0;

    @Column(name = "moisture")
    private float moisture = 0;

    @Column(name = "protein")
    private float protein = 0;

    @Column(name = "fat")
    private float fat = 0;

    @Column(name = "carbohydrate")
    private float carbohydrate = 0;

    @Column(name = "dietary_fiber")
    private float dietaryFiber = 0;

    @Column(name = "calcium")
    private float calcium = 0;

    @Column(name = "iron")
    private float iron = 0;

    @Column(name = "phosphorus")
    private float phosphorus = 0;

    @Column(name = "selenium")
    private float selenium = 0;

    @Column(name = "sodium")
    private float sodium = 0;

    @Column(name = "vitamin_a")
    private float vitaminA = 0;

    @Column(name = "vitamin_b1")
    private float vitaminB1 = 0;

    @Column(name = "vitamin_b2")
    private float vitaminB2 = 0;

    @Column(name = "vitamin_c")
    private float vitaminC = 0;

    @Column(name = "ate_date", nullable = false, updatable = false)
    private LocalDateTime ateDate = LocalDateTime.now();

    /* Food Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Builder
    public History(String classification, String detailClassification, float energy, float moisture, float protein,
                   float fat, float carbohydrate, float dietaryFiber, float calcium, float iron, float phosphorus,
                   float selenium, float sodium, float vitaminA, float vitaminB1, float vitaminB2, float vitaminC,
                   Food food, User user) {
        this.classification = classification;
        this.detailClassification = detailClassification;
        this.energy = energy;
        this.moisture = moisture;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.dietaryFiber = dietaryFiber;
        this.calcium = calcium;
        this.iron = iron;
        this.phosphorus = phosphorus;
        this.selenium = selenium;
        this.sodium = sodium;
        this.vitaminA = vitaminA;
        this.vitaminB1 = vitaminB1;
        this.vitaminB2 = vitaminB2;
        this.vitaminC = vitaminC;
        this.food = food;
        this.user = user;
    }

    /* User Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(HistoryRequestDto historyRequestDto){
        this.energy = historyRequestDto.energy();
        this.moisture = historyRequestDto.moisture();
        this.protein = historyRequestDto.protein();
        this.fat = historyRequestDto.fat();
        this.carbohydrate = historyRequestDto.carbohydrate();
        this.dietaryFiber = historyRequestDto.dietaryFiber();
        this.calcium = historyRequestDto.calcium();
        this.iron = historyRequestDto.iron();
        this.phosphorus = historyRequestDto.phosphorus();
        this.selenium = historyRequestDto.selenium();
        this.sodium = historyRequestDto.sodium();
        this.vitaminA = historyRequestDto.vitaminA();
        this.vitaminB1 = historyRequestDto.vitaminB1();
        this.vitaminB2 = historyRequestDto.vitaminB2();
        this.vitaminC = historyRequestDto.vitaminC();
    }

}
