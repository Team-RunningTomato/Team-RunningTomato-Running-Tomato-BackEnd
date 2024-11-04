package com.tomato.running.global.security.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class TokenNotValidException extends CustomException {
    public TokenNotValidException(){
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}
