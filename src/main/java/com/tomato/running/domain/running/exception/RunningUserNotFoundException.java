package com.tomato.running.domain.running.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class RunningUserNotFoundException extends CustomException {
    public RunningUserNotFoundException() {
        super(ErrorCode.RUNNING_USER_NOT_FOUND);
    }
}
