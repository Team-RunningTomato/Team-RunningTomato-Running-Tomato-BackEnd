package com.tomato.running.domain.run.presentation;

import com.tomato.running.domain.run.presentation.data.req.RecordRunningRequestDto;
import com.tomato.running.domain.run.service.RecordRunningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/run")
public class RunController {

    private final RecordRunningService recordRunningService;

    @PostMapping
    public ResponseEntity<Void> recordRunning(@RequestBody @Valid RecordRunningRequestDto dto) {
        recordRunningService.recordRunning(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
