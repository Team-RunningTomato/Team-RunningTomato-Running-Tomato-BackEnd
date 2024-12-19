package com.tomato.running.domain.user.service;

import com.tomato.running.domain.meeting.controller.data.res.GetMeetingResponseDto;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.user.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMeetingsService {

    private final MeetingRepository meetingRepository;
    private final UserUtil userUtil;

    public List<GetMeetingResponseDto> getMeetings() {
        User user = userUtil.getCurrentUser();

        return meetingRepository.findByUser(user).stream()
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
