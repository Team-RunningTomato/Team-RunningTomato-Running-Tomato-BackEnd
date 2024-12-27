package com.tomato.running.domain.run.presentation.data.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Getter
@NoArgsConstructor
public class RecordRunningRequestDto {
    @NotNull
    private Float startLongitude;

    @NotNull
    private Float startLatitude;

    @NotNull
    private Float endLongitude;

    @NotNull
    private Float endLatitude;

    @NotNull
    private Time runningTime;
}
