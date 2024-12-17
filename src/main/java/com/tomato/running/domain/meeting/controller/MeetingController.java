package com.tomato.running.domain.meeting.controller;


import com.tomato.running.domain.meeting.controller.data.req.CreateMeetingRequestDto;
import com.tomato.running.domain.meeting.service.CreateMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

    private final CreateMeetingService createMeetingService;

    @PostMapping
    public ResponseEntity<Void> care(@RequestBody CreateMeetingRequestDto dto) {
        createMeetingService.createMeeting(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
