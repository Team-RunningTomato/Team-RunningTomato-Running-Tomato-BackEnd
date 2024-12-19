package com.tomato.running.domain.auth.service;

import com.tomato.running.domain.auth.repository.RefreshTokenRepository;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.ReadOnlyTransactionService;
import lombok.RequiredArgsConstructor;

@ReadOnlyTransactionService
@RequiredArgsConstructor
public class LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserUtil userUtil;

    public void logout() {
        User user = userUtil.getCurrentUser();
        refreshTokenRepository.deleteById(user.getId());
    }
}
