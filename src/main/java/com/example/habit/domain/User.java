package com.example.habit.domain;


import com.example.habit.dto.request.RegisterRequestDto;
import com.example.habit.type.EGender;
import com.example.habit.type.EProvider;
import com.example.habit.type.ERole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "social_id", unique = true)
    private String socialId;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private EProvider eProvider;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    /* User Info */

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private EGender eGender;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    /* User Essential Nutrients Info */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_essential_nutrients_id")
    private UserEssentialNutrients userEssentialNutrients;

    // 1:N

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<History> historyList = new ArrayList<>();

    @Builder
    public User(String socialId, EProvider provider, ERole eRole) {
        this.socialId = socialId;
        this.eProvider = provider;
        this.role = eRole;
        this.createDate = LocalDate.now();
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public void register(RegisterRequestDto registerRequestDto, UserEssentialNutrients userEssentialNutrients) {
        this.age = registerRequestDto.age();
        this.eGender = registerRequestDto.gender();
        this.nickname = registerRequestDto.nickname();
        this.height = registerRequestDto.height();
        this.weight = registerRequestDto.weight();
        this.userEssentialNutrients = userEssentialNutrients;
    }

}

