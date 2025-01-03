package com.tomato.running.global.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // TOKEN
    TOKEN_NOT_VALID(401,"TOKEN_NOT_VALID"),
    TOKEN_IS_EXPIRATION(401,"TOKEN_IS_EXPIRATION"),
    TOKEN_NOT_FOUND(404, "TOKEN_NOT_FOUND"),

    // USER
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    ALREADY_EXIST_USERNAME(409, "ALREADY_EXIST_USERNAME"),

    // run
    RUNNING_USER_NOT_FOUND(404, "RUNNING_USER_NOT_FOUND"),
    INVALID_RUN_DISTANCE(400, "INVALID_RUN_DISTANCE"),

    // meeting
    MEETING_NOT_FOUND(404, "MEETING_NOT_FOUND"),
    AUTHOR_NOT_VALID(401, "AUTHOR_NOT_VALID"),
    MEMBER_NOT_VALID(401, "MEMBER_NOT_VALID"),

    // meetingMember
    ALREADY_EXIST_MEETING_MEMBER(409, "ALREADY_EXIST_MEETING_MEMBER"),
    MEETING_MEMBER_NOT_FOUND(404, "MEETING_MEMBER_NOT_FOUND"),;
    private final int status;
    private final String message;
}