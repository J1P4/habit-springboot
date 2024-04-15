package com.example.habit.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class CookieUtil {

    private static final Integer DEFAULT_MAX_AGE = 60*60*24;
    public static Optional<Cookie> getCookie(){
        return Optional.empty();
    }
    public static void addCookie(HttpServletResponse response, String name, String value){
        Cookie cookie = new Cookie(name,value);
        // 전역 사용
        cookie.setPath("/");;

        response.addCookie(cookie);
    }

    public static void addSecureCookie(HttpServletResponse response,String name, String value){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(DEFAULT_MAX_AGE);
        response.addCookie(cookie);
    }
}
