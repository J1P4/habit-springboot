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

    /* Food Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    /* User Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
