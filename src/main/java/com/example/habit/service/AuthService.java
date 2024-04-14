package com.example.habit.service;


import com.example.habit.domain.User;
import com.example.habit.dto.JwtTokenDto;
import com.example.habit.dto.request.RegisterRequestDto;
import com.example.habit.exception.CommonException;
import com.example.habit.exception.ErrorCode;
import com.example.habit.repository.UserRepository;
import com.example.habit.type.ERole;
import com.example.habit.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public JwtTokenDto register(Long userId, RegisterRequestDto registerRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        user.setRole(ERole.USER);
        user.register(registerRequestDto);

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());

        return jwtTokenDto;
    }

}
