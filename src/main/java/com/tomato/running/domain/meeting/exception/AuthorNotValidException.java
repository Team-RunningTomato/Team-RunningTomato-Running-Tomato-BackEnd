package com.tomato.running.domain.meeting.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class AuthorNotValidException extends CustomException {
    public AuthorNotValidException() {
        super(ErrorCode.AUTHOR_NOT_VALID);
    }
}
