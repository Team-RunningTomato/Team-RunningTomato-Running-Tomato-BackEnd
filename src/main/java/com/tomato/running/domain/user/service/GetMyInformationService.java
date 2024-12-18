package com.tomato.running.domain.user.service;

import com.tomato.running.domain.running.RunningUser;
import com.tomato.running.domain.running.exception.RunningUserNotFoundException;
import com.tomato.running.domain.running.repository.RunningUserRepository;
import com.tomato.running.domain.user.User;
import com.tomato.running.domain.user.controller.data.res.GetMyInformationResponseDto;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;

import lombok.RequiredArgsConstructor;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMyInformationService {

    private final UserUtil userUtil;
    private final RunningUserRepository runningUserRepository;

    public GetMyInformationResponseDto getMyInformation() {
        User user = userUtil.getCurrentUser();

        return GetMyInformationResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .height(user.getHeight())
                .weight(user.getWeight())
                .runningUser(GetMyInformationResponseDto.runningUserDto.builder()
                        .totalDistance(user.getRunningUser().getTotalDistance())
                        .bestDistance(user.getRunningUser().getBestDistance())
                        .worstDistance(user.getRunningUser().getWorstDistance())
                        .levelPercentage(user.getRunningUser().getLevelPercentage())
                        .build())
                .build();
    }
}
