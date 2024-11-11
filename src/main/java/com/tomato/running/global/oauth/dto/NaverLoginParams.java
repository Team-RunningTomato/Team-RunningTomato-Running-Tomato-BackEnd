package com.tomato.running.global.oauth.dto;
import com.tomato.running.global.oauth.OAuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
@Getter
@NoArgsConstructor
public class NaverLoginParams {
    private String authorizationCode;
    private String state;

    public OAuthProvider oAuthProvider() {
        return OAuthProvider.NAVER;
    }

    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        body.add("state", state);
        return body;
    }
}