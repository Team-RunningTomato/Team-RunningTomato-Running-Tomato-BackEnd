package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.Meeting;
import com.tomato.running.domain.meeting.controller.data.res.GetMeetingResponseDto;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ReadOnlyTransactionService
public class GetSearchMeetingService {

    private final MeetingRepository meetingRepository;
    
    public List<GetMeetingResponseDto> searchMeeting(String title) {
        List<Meeting> meetings = meetingRepository.findByTitleContaining(title);

        return meetings.stream()
                .map(meeting -> GetMeetingResponseDto.builder()
                        .id(meeting.getId())
                        .title(meeting.getTitle())
                        .startAt(String.valueOf(meeting.getStartAt()))
                        .distance(meeting.getDistance())
                        .startLocation(meeting.getStartLocation())
                        .build())
                .collect(Collectors.toList());
    }
}
