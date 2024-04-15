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
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "road_address", nullable = false)
    private String roadAddress;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "main_menu")
    private String mainMenu;

    @Column(name = "category")
    private String category;

    // 행정동명
    @Column(name = "dong")
    private String dong;


}
