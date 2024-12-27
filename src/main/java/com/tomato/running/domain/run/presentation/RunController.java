package com.tomato.running.domain.run.presentation;

import com.tomato.running.domain.run.presentation.data.req.RecordRunningRequestDto;
import com.tomato.running.domain.run.service.RecordRunningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/run")
public class RunController {

    private final RecordRunningService recordRunningService;

    @PostMapping("/{meeting_id}")
    public ResponseEntity<Void> recordRunning(@PathVariable("meeting_id") UUID meetingId, @RequestBody @Valid RecordRunningRequestDto dto) {
        recordRunningService.recordRunning(meetingId ,dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
