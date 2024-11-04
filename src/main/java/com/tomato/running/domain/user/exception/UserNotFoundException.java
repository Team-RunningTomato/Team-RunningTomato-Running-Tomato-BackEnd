package com.tomato.running.domain.user.exception;


import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}