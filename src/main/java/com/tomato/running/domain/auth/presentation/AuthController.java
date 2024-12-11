package com.tomato.running.domain.auth.presentation;

import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.domain.auth.service.LoginService;
import com.tomato.running.domain.auth.service.LogoutService;
import com.tomato.running.domain.auth.service.ReissueTokenService;
import com.tomato.running.global.oauth.dto.NaverLoginParams;
import com.tomato.running.global.security.util.count.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<TokenDto> login(@RequestBody NaverLoginParams params, HttpServletResponse response) {
        try {

            TokenDto tokenDto = loginService.login(params);

            CookieUtil.addCookie(response, "accessToken", tokenDto.getAccessToken(), 1000 * 60 * 30);
            CookieUtil.addCookie(response, "refreshToken", tokenDto.getRefreshToken(), 1000 * 60 * 60 * 24 * 7);


            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
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
