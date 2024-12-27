package com.tomato.running.domain.user.service;

import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.presentation.data.req.UpdateWeightAndHeightRequestDto;
import com.tomato.running.domain.user.repository.UserRepository;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class UpdateWeightAndHeightService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    public void updateWeightAndHeight(UpdateWeightAndHeightRequestDto dto) {
        User user = userUtil.getCurrentUser();

        user.saveWeightAndHeight(dto);

        userRepository.save(user);
    }
}
