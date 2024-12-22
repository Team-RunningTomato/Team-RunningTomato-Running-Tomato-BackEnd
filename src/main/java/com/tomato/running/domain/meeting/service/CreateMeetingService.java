package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.controller.data.req.CreateMeetingRequestDto;
import com.tomato.running.domain.meeting.entity.MeetingMember;
import com.tomato.running.domain.meeting.repository.MeetingMemberRepository;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.run.entity.EndLocation;
import com.tomato.running.domain.run.entity.StartLocation;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class CreateMeetingService {

    private final UserUtil userUtil;
    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;

    public void createMeeting(CreateMeetingRequestDto dto){
        User user = userUtil.getCurrentUser();

        Meeting meeting = Meeting.builder()
                .title(dto.getTitle())
                .distance(dto.getDistance())
                .startAt(dto.getStartAt())
                .startLocation(new StartLocation(dto.getStartLongitude(), dto.getStartLatitude()))
                .endLocation(new EndLocation(dto.getEndLongitude(), dto.getEndLatitude()))
                .user(user)
                .build();

        meetingRepository.save(meeting);

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
