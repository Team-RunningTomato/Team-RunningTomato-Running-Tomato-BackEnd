package com.tomato.running.domain.meeting.controller.data.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateMeetingRequestDto {
    @NotNull
    private String title;

    @NotNull
    private Integer distance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private Float startLongitude;

    @NotNull
    private Float startLatitude;

    @NotNull
    private String addressDetail;
}
