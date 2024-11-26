package com.tomato.running.global.security.util.count;

import com.tomato.running.global.security.jwt.TokenProvider;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@RedisHash(value = "running_refreshToken", timeToLive = TokenProvider.REFRESH_TOKEN_EXPIRE_TIME)
@Builder
@Getter
public class RefreshToken {

    @Id
    private UUID id;

    @Indexed
    private String token;
}