package com.tomato.running.domain.user.controller;

import com.tomato.running.domain.user.controller.data.req.UpdateWeightAndHeightRequestDto;
import com.tomato.running.domain.user.service.UpdateWeightAndHeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UpdateWeightAndHeightService updateWeightAndHeightService;

    @PatchMapping
    public ResponseEntity<Void> updateWeightAndHeight(@RequestBody UpdateWeightAndHeightRequestDto dto) {
        updateWeightAndHeightService.updateWeightAndHeight(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
