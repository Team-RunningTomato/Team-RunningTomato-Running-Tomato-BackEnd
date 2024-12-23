package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.exception.MeetingNotFoundException;
import com.tomato.running.domain.meeting.exception.MemberNotValidException;
import com.tomato.running.domain.meeting.repository.MeetingMemberRepository;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@TransactionService
@RequiredArgsConstructor
public class DeleteApplicationMeetingService {

    private final MeetingRepository meetingRepository;
    private final UserUtil userUtil;
    private final MeetingMemberRepository meetingMemberRepository;

    public void deleteApplicationMeeting(UUID meetingId) {

        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(MeetingNotFoundException::new);

        User currentUser = userUtil.getCurrentUser();

        if (!meetingMemberRepository.existsByMeetingAndUser(meeting, currentUser)) {
            throw new MemberNotValidException();
        }

        meetingMemberRepository.deleteByMeetingAndUser(meeting, currentUser);
    }

}
