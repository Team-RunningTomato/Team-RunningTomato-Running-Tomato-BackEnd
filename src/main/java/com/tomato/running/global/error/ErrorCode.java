package com.tomato.running.global.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // TOKEN
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED,"TOKEN_NOT_VALID"),
    TOKEN_IS_EXPIRATION(HttpStatus.UNAUTHORIZED,"TOKEN_IS_EXPIRATION"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "TOKEN_NOT_FOUND"),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND"),
    ALREADY_EXIST_USERNAME(HttpStatus.CONFLICT, "ALREADY_EXIST_USERNAME");

    private final HttpStatus httpStatus;
    private final String message;
}