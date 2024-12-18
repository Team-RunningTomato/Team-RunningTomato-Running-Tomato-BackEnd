package com.tomato.running.domain.meeting.controller.data.res;

import com.tomato.running.domain.run.StartLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMeetingInfoResponseDto {
    private String title;
    private Integer distance;
    private String startAt;
    private StartLocation startLocation;
    private AuthorInformation author;
    private Integer memberNum;

    @Getter
    @AllArgsConstructor
    public static class AuthorInformation {
        private String name;
        private Integer level;
    }

}


