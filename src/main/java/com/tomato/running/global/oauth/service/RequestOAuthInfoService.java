package com.tomato.running.global.oauth.service;

import com.tomato.running.global.oauth.NaverApiClient;
import com.tomato.running.global.oauth.OAuthProvider;
import com.tomato.running.global.oauth.dto.NaverInfoResponse;
import com.tomato.running.global.oauth.dto.NaverLoginParams;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, NaverApiClient> clients;

    public RequestOAuthInfoService(List<NaverApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(NaverApiClient::oAuthProvider, Function.identity())
        );
    }

    public NaverInfoResponse request(NaverLoginParams params) {
        NaverApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}