package com.example.habit.service;

import com.example.habit.domain.EssentialNutrients;
import com.example.habit.domain.User;
import com.example.habit.domain.UserEssentialNutrients;
import com.example.habit.dto.JwtTokenDto;
import com.example.habit.dto.request.RegisterRequestDto;
import com.example.habit.exception.CommonException;
import com.example.habit.exception.ErrorCode;
import com.example.habit.repository.EssentialNutrientsRepository;
import com.example.habit.repository.UserEssentialNutrientsRepository;
import com.example.habit.repository.UserRepository;
import com.example.habit.type.EGender;
import com.example.habit.type.ERole;
import com.example.habit.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserEssentialNutrientsRepository userEssentialNutrientsRepository;
    private final EssentialNutrientsRepository essentialNutrientsRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public JwtTokenDto register(Long userId, RegisterRequestDto registerRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        EssentialNutrients essentialNutrients = essentialNutrientsRepository.findByEGenderAndAge(registerRequestDto.gender(), registerRequestDto.age())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ESSENTIAL_NUTREINTS));

        float bmr  = (float) (((registerRequestDto.gender() == EGender.FEMALE) ?
                        447.593 + (9.247 * registerRequestDto.weight()) + (3.098 * registerRequestDto.height()) - (4.330 - registerRequestDto.age()) :
                        88.362 + (13.397 * registerRequestDto.weight()) + (4.799 * registerRequestDto.height()) - (5.677 - registerRequestDto.age()))
                        * 1.375);

        UserEssentialNutrients newUserEssentialNutrients = UserEssentialNutrients.builder()
                .energy(bmr)
                .carbohydrate((float) (bmr * 0.55))
                .fat((float) (bmr * 0.25))
                .protein(registerRequestDto.weight())
                .essentialNutrients(essentialNutrients)
                .build();
        userEssentialNutrientsRepository.save(newUserEssentialNutrients);

        user.setRole(ERole.USER);
        user.register(registerRequestDto, newUserEssentialNutrients);

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());

        return jwtTokenDto;
    }

}
