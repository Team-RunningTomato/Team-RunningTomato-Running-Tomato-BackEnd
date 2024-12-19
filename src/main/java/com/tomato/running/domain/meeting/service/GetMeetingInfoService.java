package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.controller.data.res.GetMeetingInfoResponseDto;
import com.tomato.running.domain.meeting.exception.MeetingNotFoundException;
import com.tomato.running.domain.meeting.repository.MeetingMemberRepository;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@ReadOnlyTransactionService
public class GetMeetingInfoService {

    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;

    public GetMeetingInfoResponseDto getMeetingInfo(UUID meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(MeetingNotFoundException::new);

        return GetMeetingInfoResponseDto.builder()
                .title(meeting.getTitle())
                .distance(meeting.getDistance())
                .startAt(String.valueOf(meeting.getStartAt()))
                .startLocation(meeting.getStartLocation())
                .author(new GetMeetingInfoResponseDto.AuthorInformation(meeting.getUser().getName(), meeting.getUser().getRunningUser().getLevel()))
                .memberNum(meetingMemberRepository.countAllByMeeting(meeting))
                .build();
    }
}
