package com.example.habit.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "exemplary_restaurant")
public class ExemplaryRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location_address")
    private String locationAddress;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "dong")
    private String dong;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "main_food")
    private String mainFood;

    @Column(name = "designated_date")
    private LocalDate designatedDate;
}