package com.tomato.running.domain.auth.presentation;

import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.domain.auth.service.LoginService;
import com.tomato.running.global.oauth.dto.NaverLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/naver")
    public ResponseEntity<TokenDto> login (@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(loginService.login(params));
    }
}
