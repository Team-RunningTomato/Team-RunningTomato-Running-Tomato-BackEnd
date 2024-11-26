package com.tomato.running.domain.auth.repository;

import com.tomato.running.global.security.util.count.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
