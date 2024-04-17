package com.example.habit.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "food_code", nullable = false)
    private String foodCode;

    @Column(name = "classification", nullable = false)
    private String classification;

    @Column(name = "DetailClassification")
    private String detailClassification;

    // 1회 제공량
    @Column(name = "serving_size")
    private int servingSize = 0;
    //에너지
    @Column(name = "energy")
    private float energy = 0;
    //수분
    @Column(name = "moisture")
    private float moisture = 0;
    //단백질
    @Column(name = "protein")
    private float protein = 0;
    //지방
    @Column(name = "fat")
    private float fat = 0;
    //탄수화물
    @Column(name = "carbohydrate")
    private float carbohydrate = 0;
    //식이섬유
    @Column(name = "dietary_fiber")
    private float dietaryFiber = 0;
    //칼슘
    @Column(name = "calcium")
    private float calcium = 0;
    //철분
    @Column(name = "iron")
    private float iron = 0;
    //인
    @Column(name = "phosphorus")
    private float phosphorus = 0;
    //셀레늄
    @Column(name = "selenium")
    private float selenium = 0;
    //나트륨
    @Column(name = "sodium")
    private float sodium = 0;
    //비타민A
    @Column(name = "vitamin_a")
    private float vitaminA = 0;
    //비타민B1
    @Column(name = "vitamin_b1")
    private float vitaminB1 = 0;
    //비타민B2
    @Column(name = "vitamin_b2")
    private float vitaminB2 = 0;
    //비타민C
    @Column(name = "vitamin_c")
    private float vitaminC = 0;
}
