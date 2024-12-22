package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.entity.MeetingMember;
import com.tomato.running.domain.meeting.exception.AlreadyExsistMeetingMemberException;
import com.tomato.running.domain.meeting.exception.MeetingNotFoundException;
import com.tomato.running.domain.meeting.repository.MeetingMemberRepository;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@TransactionService
@RequiredArgsConstructor
public class ApplicationMeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;
    private final UserUtil userUtil;

    public void applicationMeeting(UUID meetingId) {
        User user = userUtil.getCurrentUser();

        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(MeetingNotFoundException::new);

        if (meetingMemberRepository.existsByMeetingAndUser(meeting, user))
            throw new AlreadyExsistMeetingMemberException();

        saveMeetingMember(meeting, user);
    }

    private void saveMeetingMember(Meeting meeting, User user) {
        MeetingMember meetingMember = MeetingMember.builder()
                .meeting(meeting)
                .user(user)
                .build();

        meetingMemberRepository.save(meetingMember);
    }
}
