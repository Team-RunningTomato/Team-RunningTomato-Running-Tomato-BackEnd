package com.tomato.running.domain.auth.service;

import com.tomato.running.domain.auth.exception.TokenNotFoundException;
import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.domain.auth.repository.RefreshTokenRepository;
import com.tomato.running.global.annotation.TransactionService;
import com.tomato.running.global.security.jwt.TokenProvider;
import com.tomato.running.global.security.util.count.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@TransactionService
@RequiredArgsConstructor
public class ReissueTokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenDto reissueToken(String refreshToken) {
        String parseRefreshToken = tokenProvider.parseRefreshToken(refreshToken);

        RefreshToken findRefreshToken = refreshTokenRepository.findByToken(parseRefreshToken)
                .orElseThrow(TokenNotFoundException::new);

        refreshTokenRepository.deleteById(findRefreshToken.getUserId());

        TokenDto tokenDto = tokenProvider.generateTokenDto(findRefreshToken.getUserId());

        saveRefreshToken(tokenDto.getRefreshToken(), findRefreshToken.getUserId());

        return tokenDto;
    }

    private void saveRefreshToken(String token, UUID id) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(id)
                .token(token)
                .build();

        refreshTokenRepository.save(refreshToken);
    }

}
