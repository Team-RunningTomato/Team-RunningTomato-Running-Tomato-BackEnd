package com.tomato.running.global.security.jwt;

import com.tomato.running.domain.auth.presentation.data.res.TokenDto;
import com.tomato.running.global.auth.AuthDetailsService;
import com.tomato.running.global.security.exception.TokenExpirationException;
import com.tomato.running.global.security.exception.TokenNotValidException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {

    private final AuthDetailsService authDetailsService;
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer ";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 60L * 30;
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 60L * 60 * 24 * 7;


    private final Key key;


    public TokenProvider(@Value("${jwt.secret}") String secret, AuthDetailsService authDetailsService) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.authDetailsService = authDetailsService;
    }

    public TokenDto generateTokenDto(UUID userid){
        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(generateAccessToken(userid))
                .refreshToken(generateRefreshToken(userid))
                .accessTokenExpiresIn(ACCESS_TOKEN_EXPIRE_TIME)
                .refreshTokenExpiresIn(REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }


    private String generateAccessToken(UUID userid) {
        Date accessTokenExpiresIn = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME * 1000);

        return Jwts.builder()
                .setSubject(userid.toString())
                .claim(AUTHORITIES_KEY, "JWT")
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private String generateRefreshToken(UUID userid) {
        Date refreshTokenExpiresIn = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME * 1000);

        return Jwts.builder()
                .setSubject(userid.toString())
                .claim(AUTHORITIES_KEY, "JWT")
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken){
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
        if(claims.get(AUTHORITIES_KEY) == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        UUID userid = UUID.fromString(claims.getSubject());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = authDetailsService.loadUserByUsername(userid.toString());
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());

    }

    public String parseRefreshToken(String refreshToken){
        if (refreshToken.startsWith(BEARER_TYPE)) {
            return refreshToken.replace(BEARER_TYPE, "");

        } else {
            return null;
        }
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new TokenExpirationException();
        } catch (JwtException e) {
            throw new TokenNotValidException();
        }
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }


}
