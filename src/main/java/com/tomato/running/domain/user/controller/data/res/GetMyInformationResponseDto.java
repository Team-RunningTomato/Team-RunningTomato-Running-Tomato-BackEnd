package com.tomato.running.domain.user.controller.data.res;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class GetMyInformationResponseDto {
    private UUID id;
    private String name;
    private String weight;
    private String height;
    private runningUserDto runningUser;

    @Builder
    @Getter
    public static class runningUserDto {
        private Integer totalDistance;
        private Integer bestDistance;
        private Integer worstDistance;
        private Integer levelPercentage;
    }
}
