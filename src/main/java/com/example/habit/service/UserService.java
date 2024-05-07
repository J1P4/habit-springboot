package com.example.habit.service;

import com.example.habit.dto.response.NicknameResponseDto;
import com.example.habit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public NicknameResponseDto getNickname(Long userId) {
        return new NicknameResponseDto(userRepository.findById(userId).get().getNickname());
    }
}
