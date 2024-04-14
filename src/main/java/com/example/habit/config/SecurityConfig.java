package com.example.habit.config;


import com.example.habit.constant.Constant;
import com.example.habit.security.JwtAuthEntryPoint;
import com.example.habit.security.JwtAuthenticationProvider;
import com.example.habit.security.filter.JwtAuthenticationFilter;
import com.example.habit.security.filter.JwtExceptionFilter;
import com.example.habit.security.handler.*;
import com.example.habit.security.service.CustomOAuth2UserService;
import com.example.habit.security.service.CustomUserDetailService;
import com.example.habit.type.ERole;
import com.example.habit.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;

    private final JwtUtil jwtUtil;

    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomLogOutProcessHandler customLogOutProcessHandler;
    private final CustomLogOutResultHandler customLogOutResultHandler;

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

    @Bean
    protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                        .requestMatchers("api/v1/auth/**").permitAll()
                        .requestMatchers("api/v1/users/**").hasAnyRole((ERole.USER.toString()),ERole.ADMIN.toString())
                        .requestMatchers("api/v1/admin/**").hasRole(ERole.ADMIN.toString())
                        .anyRequest().authenticated())

                .oauth2Login(
                        configurer ->
                                configurer
                                        .successHandler(oAuth2LoginSuccessHandler)
                                        .failureHandler(oAuth2LoginFailureHandler)
                                        .userInfoEndpoint(userInfoEndpoint ->
                                                userInfoEndpoint.userService(customOAuth2UserService))
                )

                .logout(configurer ->
                        configurer
                                .logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(customLogOutProcessHandler)
                                .logoutSuccessHandler(customLogOutResultHandler)
                                .deleteCookies(Constant.AUTHORIZATION, Constant.REAUTHORIZATION)
                )
                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, new JwtAuthenticationProvider(customUserDetailService)), LogoutFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
                .getOrBuild();
    }
}
