package com.example.habit.repository;

import com.example.habit.domain.ExemplaryRestaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaryRestaurantRepository extends JpaRepository<ExemplaryRestaurant, Long> {
    Slice<ExemplaryRestaurant> findByDongContaining(String dong, Pageable pageInfo);
}
