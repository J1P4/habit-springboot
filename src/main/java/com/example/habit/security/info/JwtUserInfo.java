package com.example.habit.security.info;


import com.example.habit.type.ERole;

public record JwtUserInfo(Long id, ERole role) {

}
