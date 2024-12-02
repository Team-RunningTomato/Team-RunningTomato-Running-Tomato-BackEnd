package com.tomato.running.domain.auth.presentation;

import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.domain.auth.service.LoginService;
import com.tomato.running.domain.auth.service.LogoutService;
import com.tomato.running.domain.auth.service.ReissueTokenService;
import com.tomato.running.global.oauth.dto.NaverLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;
    private final ReissueTokenService reissueTokenService;
    private final LogoutService logoutService;

    @PostMapping("/naver")
    public ResponseEntity<TokenDto> login (@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(loginService.login(params));
    }

    @PatchMapping
    public ResponseEntity<TokenDto> reissueToken(@RequestHeader String refreshToken) {
        return ResponseEntity.ok(reissueTokenService.reissueToken(refreshToken));
    }

    @DeleteMapping
    public ResponseEntity<Void> logout() {
        logoutService.logout();
        return ResponseEntity.noContent().build();
    }
}
