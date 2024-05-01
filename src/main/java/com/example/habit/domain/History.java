package com.example.habit.domain;

import com.example.habit.dto.request.HistoryRequestDto;
import com.example.habit.type.ETime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

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

    @Column(name = "time")
    @Enumerated(EnumType.STRING)
    private ETime time;

    /* Food Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Builder
    public History(String classification, String detailClassification, float energy, float moisture, float protein,
                   float fat, float carbohydrate, float dietaryFiber, float calcium, float iron, float phosphorus,
                   float selenium, float sodium, float vitaminA, float vitaminB1, float vitaminB2, float vitaminC,
                   Food food, User user, ETime time) {
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
        this.time = time;
    }

    /* User Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(HistoryRequestDto historyRequestDto) {
        if (historyRequestDto.energy() != 0){
            this.energy = historyRequestDto.energy();
        }
        if (historyRequestDto.moisture() != 0){
            this.moisture = historyRequestDto.moisture();
        }
        if (historyRequestDto.protein() != 0){
            this.protein = historyRequestDto.protein();
        }
        if (historyRequestDto.fat() != 0){
            this.fat = historyRequestDto.fat();
        }
        if (historyRequestDto.carbohydrate() != 0){
            this.carbohydrate = historyRequestDto.carbohydrate();
        }
        if (historyRequestDto.dietaryFiber() != 0){
            this.dietaryFiber = historyRequestDto.dietaryFiber();
        }
        if (historyRequestDto.calcium() != 0){
            this.calcium = historyRequestDto.calcium();
        }
        if (historyRequestDto.iron() != 0){
            this.iron = historyRequestDto.iron();
        }
        if (historyRequestDto.phosphorus() != 0){
            this.phosphorus = historyRequestDto.phosphorus();
        }
        if (historyRequestDto.selenium() != 0){
            this.selenium = historyRequestDto.selenium();
        }
        if (historyRequestDto.sodium() != 0){
            this.sodium = historyRequestDto.sodium();
        }
        if (historyRequestDto.vitaminA() != 0){
            this.vitaminA = historyRequestDto.vitaminA();
        }
        if (historyRequestDto.vitaminB1() != 0){
            this.vitaminB1 = historyRequestDto.vitaminB1();
        }
        if (historyRequestDto.vitaminB2() != 0){
            this.vitaminB2 = historyRequestDto.vitaminB2();
        }
        if (historyRequestDto.vitaminC() != 0){
            this.vitaminC = historyRequestDto.vitaminC();
        }
    }

    public static History getZeroHistory() {
        return History.builder()
                .energy(0)
                .protein(0)
                .fat(0)
                .carbohydrate(0)
                .dietaryFiber(0)
                .calcium(0)
                .iron(0)
                .phosphorus(0)
                .selenium(0)
                .sodium(0)
                .vitaminA(0)
                .vitaminB1(0)
                .vitaminB2(0)
                .vitaminC(0)
                .food(new Food())
                .time(ETime.NONE)
                .build();
    }

    public void plus(History history) {
        this.energy += history.getEnergy();
        this.protein += history.getProtein();
        this.fat += history.getFat();
        this.carbohydrate += history.getCarbohydrate();
        this.dietaryFiber += history.getDietaryFiber();
        this.calcium += history.getCalcium();
        this.iron += history.getIron();
        this.phosphorus += history.getPhosphorus();
        this.selenium += history.getSelenium();
        this.sodium += history.getSodium();
        this.vitaminA += history.getVitaminA();
        this.vitaminB1 += history.getVitaminB1();
        this.vitaminB2 += history.getVitaminB2();
        this.vitaminC += history.getVitaminC();
    }

}
