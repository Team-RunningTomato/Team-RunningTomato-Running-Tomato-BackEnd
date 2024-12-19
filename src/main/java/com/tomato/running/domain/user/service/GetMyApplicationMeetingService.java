package com.tomato.running.domain.user.service;

import com.tomato.running.domain.meeting.Meeting;
import com.tomato.running.domain.meeting.controller.data.res.GetMeetingResponseDto;
import com.tomato.running.domain.meeting.repository.MeetingMemberRepository;
import com.tomato.running.domain.user.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMyApplicationMeetingService {

    private final MeetingMemberRepository meetingMemberRepository;
    private final UserUtil userUtil;
    public List<GetMeetingResponseDto> getMyApplicationMeeting() {

        User currentUser = userUtil.getCurrentUser();
        List<Meeting> meetings = meetingMemberRepository.findMeetingsByUser(currentUser);

        return meetings.stream()
                .map(meeting -> GetMeetingResponseDto.builder()
                        .id(meeting.getId())
                        .title(meeting.getTitle())
                        .startAt(meeting.getStartAt().toString())
                        .distance(meeting.getDistance())
                        .startLocation(meeting.getStartLocation())
                        .build())
                .collect(Collectors.toList());
    }
}
