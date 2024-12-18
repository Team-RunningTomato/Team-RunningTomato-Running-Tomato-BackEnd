package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.controller.data.res.GetMeetingResponseDto;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyTransactionService
public class GetMeetingListService {

    private final MeetingRepository meetingRepository;

    public List<GetMeetingResponseDto> getMeetingList() {
        return meetingRepository.findAll().stream()
                .map(meeting -> GetMeetingResponseDto.builder()
                        .id(meeting.getId())
                        .title(meeting.getTitle())
                        .startAt(String.valueOf(meeting.getStartAt()))
                        .distance(meeting.getDistance())
                        .startLocation(meeting.getStartLocation())
                        .build()
                ).toList();
    }
}
