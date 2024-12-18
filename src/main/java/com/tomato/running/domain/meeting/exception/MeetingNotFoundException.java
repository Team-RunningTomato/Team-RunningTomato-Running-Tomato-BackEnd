package com.tomato.running.domain.meeting.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class MeetingNotFoundException extends CustomException {
    public MeetingNotFoundException() {
        super(ErrorCode.MEETING_NOT_FOUND);
    }
}
