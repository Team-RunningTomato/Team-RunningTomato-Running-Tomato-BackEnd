package com.tomato.running.domain.auth.service;

import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.domain.auth.repository.RefreshTokenRepository;
import com.tomato.running.domain.running.entity.RunningUser;
import com.tomato.running.domain.running.repository.RunningUserRepository;
import com.tomato.running.domain.user.entity.Role;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.repository.UserRepository;
import com.tomato.running.global.annotation.TransactionService;
import com.tomato.running.global.oauth.dto.NaverInfoResponse;
import com.tomato.running.global.oauth.dto.NaverLoginParams;
import com.tomato.running.global.oauth.service.RequestOAuthInfoService;
import com.tomato.running.global.security.jwt.TokenProvider;
import com.tomato.running.global.security.util.count.RefreshToken;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@TransactionService
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RequestOAuthInfoService requestOAuthInfoService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RunningUserRepository runningUserRepository;

    public TokenDto login(NaverLoginParams params) {
        NaverInfoResponse naverInfoResponse = requestOAuthInfoService.request(params);
        User user = findOrCreateMember(naverInfoResponse);

        TokenDto tokenDto = tokenProvider.generateTokenDto(user.getId());

        saveRefreshToken(tokenDto.getRefreshToken(), user.getId());

        return tokenDto;
    }

    private User findOrCreateMember(NaverInfoResponse naverInfoResponse) {
        return userRepository.findByEmail(naverInfoResponse.getEmail())
                .orElseGet(() -> newUser(naverInfoResponse));
    }

    private User newUser(NaverInfoResponse naverInfoResponse) {
        RunningUser runningUser = saveRunningUser();

        User user = User.builder()
                .email(naverInfoResponse.getEmail())
                .name(naverInfoResponse.getName())
                .gender(naverInfoResponse.getGender())
                .age(naverInfoResponse.getAge())
                .mobile(naverInfoResponse.getMobile())
                .role(Role.ROLE_USER)
                .runningUser(runningUser)
                .build();

        return userRepository.save(user);
    }

    private void saveRefreshToken(String token, UUID id) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(id)
                .token(token)
                .build();

        refreshTokenRepository.save(refreshToken);
    }

    private RunningUser saveRunningUser() {
        RunningUser runningUser = RunningUser.builder()
                .totalDistance(0)
                .bestDistance(0)
                .worstDistance(null)
                .levelPercentage(0)
                .level(0)
                .build();

        runningUserRepository.save(runningUser);

        return runningUser;
    }
}
