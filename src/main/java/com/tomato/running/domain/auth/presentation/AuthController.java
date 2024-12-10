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

//    @PostMapping("/naver")
//    public ResponseEntity<TokenDto> login (@RequestBody NaverLoginParams params) {
//        return ResponseEntity.ok(loginService.login(params));
//    }


    @PostMapping("/naver")
    public ResponseEntity<TokenDto> login(@RequestBody NaverLoginParams params, HttpServletResponse response) {
        try {
            // 로그인 서비스 호출
            TokenDto tokenDto = loginService.login(params);

            // 액세스 토큰을 쿠키에 설정
            CookieUtil.addCookie(response, "accessToken", tokenDto.getAccessToken(), 60 * 60 * 24 * 7); // 7일 동안 유효

            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            // 로그인 실패 처리 (예시로 BAD_REQUEST 응답을 반환)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
