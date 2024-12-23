package com.tomato.running.domain.meeting.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class MemberNotValidException extends CustomException {
    public MemberNotValidException(){
        super(ErrorCode.MEMBER_NOT_VALID);
    }

}
