package com.tomato.running.domain.auth.service;

import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.domain.user.User;
import com.tomato.running.domain.user.repository.UserRepository;
import com.tomato.running.global.oauth.dto.NaverInfoResponse;
import com.tomato.running.global.oauth.dto.NaverLoginParams;
import com.tomato.running.global.oauth.service.RequestOAuthInfoService;
import com.tomato.running.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public TokenDto login(NaverLoginParams params) {
        NaverInfoResponse naverInfoResponse = requestOAuthInfoService.request(params);
        UUID userId = findOrCreateMember(naverInfoResponse);

        TokenDto tokenDto = tokenProvider.generateTokenDto(userId);

        return tokenProvider.generateTokenDto(userId);
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
                .birthday(naverInfoResponse.getBirthday())
                .age(naverInfoResponse.getAge())
                .mobile(naverInfoResponse.getMobile())
                .birthyear(naverInfoResponse.getBirthyear())
                .build();

        return userRepository.save(user).getId();
    }



}
