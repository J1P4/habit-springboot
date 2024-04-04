package com.example.habit.constant;

import java.util.List;

public class Constant {
    public static final String USER_ID_CLAIM_NAME = "uid";
    public static final String USER_ROLE_CLAIM_NAME = "rol";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "accessToken";
    public static final String REAUTHORIZATION = "refreshToken";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final List<String> NO_NEED_AUTH_URLS = List.of(
            "/login/oauth2/code/kakao",
            "/oauth2/authorization/kakao",
            "/login/oauth2/code/google",
            "/oauth2/authorization/google",
            "/login/oauth2/code/naver",
            "/oauth2/authorization/naver",
            "api/v1/auth/register"
    );
}
