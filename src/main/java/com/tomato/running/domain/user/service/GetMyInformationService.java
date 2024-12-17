package com.tomato.running.domain.user.service;

import com.tomato.running.domain.user.User;
import com.tomato.running.domain.user.controller.data.res.GetMyInformationResponseDto;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class GetMyInformationService {

    private final UserUtil userUtil;

    public GetMyInformationResponseDto getMyInformation() {
        User user = userUtil.getCurrentUser();

        return GetMyInformationResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .height(user.getHeight())
                .weight(user.getWeight())
                .build();
    }
}
