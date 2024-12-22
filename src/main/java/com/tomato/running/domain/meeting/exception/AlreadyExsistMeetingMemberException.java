package com.tomato.running.domain.meeting.exception;

import com.tomato.running.global.error.CustomException;
import com.tomato.running.global.error.ErrorCode;

public class AlreadyExsistMeetingMemberException extends CustomException {
    public AlreadyExsistMeetingMemberException() {
        super(ErrorCode.ALREADY_EXIST_MEETING_MEMBER);
    }
}
