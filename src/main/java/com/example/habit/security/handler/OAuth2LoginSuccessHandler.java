package com.example.habit.security.handler;


import com.example.habit.constant.Constant;
import com.example.habit.dto.JwtTokenDto;
import com.example.habit.repository.UserRepository;
import com.example.habit.security.info.UserPrincipal;
import com.example.habit.type.ERole;
import com.example.habit.util.CookieUtil;
import com.example.habit.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(userPrincipal.getId(), userPrincipal.getRole());

        CookieUtil.addSecureCookie(response, Constant.AUTHORIZATION, jwtTokenDto.getAccessToken());
        CookieUtil.addSecureCookie(response, "role", userPrincipal.getRole().toString());

        // Guest 즉, 소셜로그인으로 방금 가입한 유저는 따로 로직 분류

        if (userPrincipal.getRole().equals(ERole.GUEST)) {
            response.sendRedirect( "http://localhost:3000/register");
        } else {
            response.sendRedirect("http://localhost:3000/redirect");
        }

    }
}
