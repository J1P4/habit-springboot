package com.example.habit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {


    // Bad Request Error
    NOT_END_POINT(40000, HttpStatus.BAD_REQUEST, "End Point가 존재하지 않습니다."),
    NOT_FOUND_RESOURCE(40000, HttpStatus.BAD_REQUEST, "해당 리소스가 존재하지 않습니다."),
    INVALID_ARGUMENT(40001, HttpStatus.BAD_REQUEST, "Invalid Argument"),
    INVALID_PROVIDER(40002, HttpStatus.BAD_REQUEST, "유효하지 않은 제공자입니다."),
    METHOD_NOT_ALLOWED(40003, HttpStatus.BAD_REQUEST, "지원하지 않는 HTTP Method 입니다."),
    UNSUPPORTED_MEDIA_TYPE(40004, HttpStatus.BAD_REQUEST, "지원하지 않는 미디어 타입입니다."),
    MISSING_REQUEST_PARAMETER(40005, HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH(40006, HttpStatus.BAD_REQUEST, "요청 파라미터의 형태가 잘못되었습니다."),
    DUPLICATE_RESOURCE(40007, HttpStatus.BAD_REQUEST, "중복된 리소스입니다."),

    EXPIRED_TOKEN_ERROR(40100, HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    INVALID_TOKEN_ERROR(40101, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_MALFORMED_ERROR(40102, HttpStatus.UNAUTHORIZED, "토큰이 올바르지 않습니다."),
    TOKEN_TYPE_ERROR(40103, HttpStatus.UNAUTHORIZED, "토큰 타입이 일치하지 않습니다."),
    TOKEN_UNSUPPORTED_ERROR(40104, HttpStatus.UNAUTHORIZED, "지원하지않는 토큰입니다."),
    TOKEN_GENERATION_ERROR(40105, HttpStatus.UNAUTHORIZED, "토큰 생성에 실패하였습니다."),
    FAILURE_LOGIN(40106, HttpStatus.UNAUTHORIZED, "로그인에 실패하였습니다."),
    FAILURE_LOGOUT(40107, HttpStatus.UNAUTHORIZED, "로그아웃에 실패하였습니다."),
    TOKEN_UNKNOWN_ERROR(40106, HttpStatus.UNAUTHORIZED, "알 수 없는 토큰입니다."),

    FILE_UPLOAD_ERROR(42201, HttpStatus.UNPROCESSABLE_ENTITY, "파일 업로드에 실패하였습니다."),

    ACCESS_DENIED_ERROR(40300, HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    // Not Found Error
    NOT_FOUND_USER(40401, HttpStatus.NOT_FOUND, "해당 사용자가 존재하지 않습니다."),
    NOT_FOUND_FOOD(40402, HttpStatus.NOT_FOUND, "해당 음식이 존재하지 않습니다."),
    NOT_FOUND_HISTORY(40403, HttpStatus.NOT_FOUND, "해당 기록이 존재하지 않습니다."),
    NOT_FOUND_FOOD_FOR_NUTRIENT(40404, HttpStatus.NOT_FOUND, "영양 상태에 맞는 음식이 존재하지 않습니다."),
    NOT_FOUND_ESSENTIAL_NUTREINTS(40405,HttpStatus.NOT_FOUND, "권장 영양소가 존재하지 않습니다."),

    SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러 입니다."),
    AUTH_SERVER_USER_INFO_ERROR(50001, HttpStatus.INTERNAL_SERVER_ERROR, "인증 서버 사용자 정보 조회에 실패하였습니다."),
    REST_CLIENT_ERROR(50002,HttpStatus.INTERNAL_SERVER_ERROR, "서버 통신 에러입니다.");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
