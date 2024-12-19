package com.tomato.running.domain.meeting.controller;


import com.tomato.running.domain.meeting.controller.data.req.CreateMeetingRequestDto;
import com.tomato.running.domain.meeting.controller.data.res.GetMeetingInfoResponseDto;
import com.tomato.running.domain.meeting.controller.data.res.GetMeetingResponseDto;
import com.tomato.running.domain.meeting.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

    private final CreateMeetingService createMeetingService;
    private final GetMeetingListService getMeetingListService;
    private final GetMeetingInfoService getMeetingInfoService;
    private final UpdateMeetingService updateMeetingService;
    private final DeleteMeetingService deleteMeetingService;
    private final GetSearchMeetingService getSearchMeetingService;

    @PostMapping
    public ResponseEntity<Void> creatMeeting(@RequestBody @Valid CreateMeetingRequestDto dto) {
        createMeetingService.createMeeting(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<GetMeetingResponseDto>> getMeetingList() {
        return ResponseEntity.ok(getMeetingListService.getMeetingList());
    }

    @GetMapping("/{meeting_id}")
    public ResponseEntity<GetMeetingInfoResponseDto> getMeetingInfo(@PathVariable("meeting_id") UUID meetingId) {
        return ResponseEntity.ok(getMeetingInfoService.getMeetingInfo(meetingId));
    }

    @PatchMapping("/{meeting_id}")
    public  ResponseEntity<Void> updateMeeting(@PathVariable("meeting_id") UUID meetingId, @RequestBody @Valid CreateMeetingRequestDto dto){
        updateMeetingService.updateMeeting(dto, meetingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{meeting_id}")
    public  ResponseEntity<Void> deleteMeeting(@PathVariable("meeting_id") UUID meetingId){
        deleteMeetingService.deleteMeeting(meetingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetMeetingResponseDto>> searchMeeting(@RequestParam String title) {
        List<GetMeetingResponseDto> meetingList = getSearchMeetingService.searchMeeting(title);
        return ResponseEntity.ok(meetingList);
    }

}
