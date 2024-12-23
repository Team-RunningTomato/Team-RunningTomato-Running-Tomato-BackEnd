package com.tomato.running.global.security.util.count;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@RedisHash(value = "running_refreshToken", timeToLive = 60L * 60 * 24 * 7)
@Builder
@Getter
public class RefreshToken {

    @Id
    private UUID userId;

    @Indexed
    private String token;
}