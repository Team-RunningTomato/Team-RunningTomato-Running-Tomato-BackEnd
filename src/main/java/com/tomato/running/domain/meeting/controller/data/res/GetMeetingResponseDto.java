package com.tomato.running.domain.meeting.controller.data.res;

import com.tomato.running.domain.run.StartLocation;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class GetMeetingResponseDto {
    private UUID id;
    private String title;
    private Integer distance;
    private String startAt;
    private StartLocation startLocation;
}
