package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.controller.data.res.GetMeetingInfoResponseDto;
import com.tomato.running.domain.meeting.exception.MeetingNotFoundException;
import com.tomato.running.domain.meeting.repository.MeetingMemberRepository;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.running.entity.RunningUser;
import com.tomato.running.domain.running.exception.RunningUserNotFoundException;
import com.tomato.running.domain.running.repository.RunningUserRepository;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@ReadOnlyTransactionService
public class GetMeetingInfoService {

    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;
    private final RunningUserRepository runningUserRepository;

    public GetMeetingInfoResponseDto getMeetingInfo(UUID meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(MeetingNotFoundException::new);

        RunningUser runningUser = runningUserRepository.findByUser(meeting.getUser())
                .orElseThrow(RunningUserNotFoundException::new);

        return GetMeetingInfoResponseDto.builder()
                .title(meeting.getTitle())
                .distance(meeting.getDistance())
                .startAt(String.valueOf(meeting.getStartAt()))
                .startLocation(meeting.getStartLocation())
                .addressDetail(meeting.getAddressDetail())
                .author(new GetMeetingInfoResponseDto.AuthorInformation(meeting.getUser().getName(), runningUser.getLevel()))
                .memberNum(meetingMemberRepository.countAllByMeeting(meeting))
                .build();
    }
}
