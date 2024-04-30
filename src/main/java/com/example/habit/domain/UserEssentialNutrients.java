package com.example.habit.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "user_essential_nutrients")
public class UserEssentialNutrients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "protein")
    private float protein = 0;

    @Column(name = "fat")
    private float fat = 0;

    @Column(name = "carbohydrate")
    private float carbohydrate = 0;

    /* Essential Nutrients Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "essential_nutrients_id")
    private EssentialNutrients essentialNutrients;

    @Builder
    public UserEssentialNutrients(float protein, float fat, float carbohydrate, EssentialNutrients essentialNutrients) {
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.essentialNutrients = essentialNutrients;
    }

    public void updateUserEssentialNutrients(float protein, float fat, float carbohydrate, EssentialNutrients essentialNutrients) {
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.essentialNutrients = essentialNutrients;
    }

}
