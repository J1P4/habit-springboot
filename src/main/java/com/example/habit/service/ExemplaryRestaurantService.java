package com.example.habit.service;

import com.example.habit.domain.ExemplaryRestaurant;
import com.example.habit.dto.response.RestaurantDto;
import com.example.habit.repository.ExemplaryRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExemplaryRestaurantService {

    private final ExemplaryRestaurantRepository exemplaryRestaurantRepository;

    public List<RestaurantDto> getRestaurantList(String locationAddress, int pageIndex, int pageSize) {

        if (locationAddress == null || locationAddress.trim().isEmpty()) {
            locationAddress = "";
        }

        Slice<ExemplaryRestaurant> restaurants = exemplaryRestaurantRepository
                .findByLocationAddressContaining(locationAddress, PageRequest.of(pageIndex, pageSize));

        return restaurants.stream()
                .map(RestaurantDto::fromEntity)
                .collect(Collectors.toList());
    }

}
