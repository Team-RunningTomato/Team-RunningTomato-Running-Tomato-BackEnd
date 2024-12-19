package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.exception.AuthorNotValidException;
import com.tomato.running.domain.meeting.exception.MeetingNotFoundException;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@TransactionService
@RequiredArgsConstructor
public class DeleteMeetingService {
    private final MeetingRepository meetingRepository;
    private final UserUtil userUtil;

    public void deleteMeeting(UUID meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(MeetingNotFoundException::new);

        User currentUser = userUtil.getCurrentUser();

        if (!meeting.getUser().equals(currentUser)) {
            throw new AuthorNotValidException();
        }

        meetingRepository.delete(meeting);

    }
}
