package com.tomato.running.domain.meeting.service;

import com.tomato.running.domain.meeting.Meeting;
import com.tomato.running.domain.meeting.controller.data.req.CreateMeetingRequestDto;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.run.EndLocation;
import com.tomato.running.domain.run.StartLocation;
import com.tomato.running.domain.user.User;
import com.tomato.running.domain.user.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateMeetingService {

    private final UserUtil userUtil;
    private final MeetingRepository meetingRepository;
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

    }
}
