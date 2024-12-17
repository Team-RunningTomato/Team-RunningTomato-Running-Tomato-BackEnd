package com.tomato.running.domain.auth.service;

import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.domain.auth.repository.RefreshTokenRepository;
import com.tomato.running.domain.user.Role;
import com.tomato.running.domain.user.User;
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

    public TokenDto login(NaverLoginParams params) {
        NaverInfoResponse naverInfoResponse = requestOAuthInfoService.request(params);
        UUID userId = findOrCreateMember(naverInfoResponse);

        TokenDto tokenDto = tokenProvider.generateTokenDto(userId);

        saveRefreshToken(tokenDto.getRefreshToken(), userId);

        return tokenDto;
    }

    private UUID findOrCreateMember(NaverInfoResponse naverInfoResponse) {
        return userRepository.findByEmail(naverInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newUser(naverInfoResponse));
    }

    private UUID newUser(NaverInfoResponse naverInfoResponse) {
        User user = User.builder()
                .email(naverInfoResponse.getEmail())
                .name(naverInfoResponse.getName())
                .gender(naverInfoResponse.getGender())
                .age(naverInfoResponse.getAge())
                .mobile(naverInfoResponse.getMobile())
                .role(Role.ROLE_USER)
                .build();

        return userRepository.save(user).getId();
    }

    private void saveRefreshToken(String token, UUID id) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(id)
                .token(token)
                .build();

        refreshTokenRepository.save(refreshToken);
    }



}
