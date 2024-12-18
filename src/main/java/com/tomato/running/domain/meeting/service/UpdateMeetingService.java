package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.Meeting;
import com.tomato.running.domain.meeting.controller.data.req.CreateMeetingRequestDto;
import com.tomato.running.domain.meeting.exception.AuthorNotValidException;
import com.tomato.running.domain.meeting.exception.MeetingNotFoundException;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.run.EndLocation;
import com.tomato.running.domain.run.StartLocation;
import com.tomato.running.domain.user.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@TransactionService
@RequiredArgsConstructor
public class UpdateMeetingService {

    private final MeetingRepository meetingRepository;
    private final UserUtil userUtil;

    public void updateMeeting(CreateMeetingRequestDto dto, UUID meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(MeetingNotFoundException::new);

        User currentUser = userUtil.getCurrentUser();

        if (!meeting.getUser().equals(currentUser)) {
            throw new AuthorNotValidException();
        }

        meeting.updateMeeting(
                dto.getTitle(),
                dto.getDistance(),
                dto.getStartAt(),
                new StartLocation(dto.getStartLongitude(), dto.getStartLatitude()),
                new EndLocation(dto.getEndLongitude(), dto.getEndLatitude())
        );
    }
}
