package com.tomato.running.domain.run.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class InvalidRunDistanceException extends CustomException {
    public InvalidRunDistanceException() {
        super(ErrorCode.INVALID_RUN_DISTANCE);
    }
}
