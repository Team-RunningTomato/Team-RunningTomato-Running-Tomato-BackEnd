package com.tomato.running.domain.user.controller;

import com.tomato.running.domain.meeting.controller.data.res.GetMeetingResponseDto;
import com.tomato.running.domain.user.controller.data.req.UpdateWeightAndHeightRequestDto;
import com.tomato.running.domain.user.controller.data.res.GetMyInformationResponseDto;
import com.tomato.running.domain.user.service.GetMeetingsService;
import com.tomato.running.domain.user.service.GetMyApplicationMeetingService;
import com.tomato.running.domain.user.service.GetMyInformationService;
import com.tomato.running.domain.user.service.UpdateWeightAndHeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UpdateWeightAndHeightService updateWeightAndHeightService;
    private final GetMyInformationService getMyInformationService;
    private final GetMeetingsService getMeetingsService;
    private final GetMyApplicationMeetingService getMyApplicationMeetingList;

    @PatchMapping
    public ResponseEntity<Void> updateWeightAndHeight(@RequestBody UpdateWeightAndHeightRequestDto dto) {
        updateWeightAndHeightService.updateWeightAndHeight(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<GetMyInformationResponseDto> getMyInformation() {
        return ResponseEntity.ok(getMyInformationService.getMyInformation());
    }

    @GetMapping("/meetings")
    public ResponseEntity<List<GetMeetingResponseDto>> getMeetings() {
        return ResponseEntity.ok(getMeetingsService.getMeetings());
    }

    @GetMapping("/meetings/application")
    public ResponseEntity<List<GetMeetingResponseDto>> getMyApplicationMeeting() {
        return ResponseEntity.ok(getMyApplicationMeetingList.getMyApplicationMeeting());
    }
}
