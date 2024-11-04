package com.tomato.running.global.security.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class TokenExpirationException extends CustomException {
    public TokenExpirationException(){
        super(ErrorCode.TOKEN_IS_EXPIRATION);
    }
}
