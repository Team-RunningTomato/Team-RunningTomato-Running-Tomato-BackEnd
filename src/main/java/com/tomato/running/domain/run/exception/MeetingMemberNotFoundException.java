package com.tomato.running.domain.run.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class MeetingMemberNotFoundException extends CustomException {
    public MeetingMemberNotFoundException() {
        super(ErrorCode.MEETING_MEMBER_NOT_FOUND);
    }
}
