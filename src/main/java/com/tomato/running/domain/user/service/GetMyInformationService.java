package com.tomato.running.domain.user.service;

import com.tomato.running.domain.running.entity.RunningUser;
import com.tomato.running.domain.running.exception.RunningUserNotFoundException;
import com.tomato.running.domain.running.repository.RunningUserRepository;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.presentation.data.res.GetMyInformationResponseDto;
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

        RunningUser runningUser = runningUserRepository.findByUser(user)
                .orElseThrow(RunningUserNotFoundException::new);

        return GetMyInformationResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .height(user.getHeight())
                .weight(user.getWeight())
                .runningUser(GetMyInformationResponseDto.runningUserDto.builder()
                        .totalDistance(runningUser.getTotalDistance())
                        .bestDistance(runningUser.getBestDistance())
                        .worstDistance(runningUser.getWorstDistance())
                        .levelPercentage(runningUser.getLevelPercentage())
                        .level(runningUser.getLevel())
                        .build())
                .build();
    }
}
