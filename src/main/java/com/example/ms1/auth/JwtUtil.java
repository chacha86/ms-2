package com.example.ms1.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {


    enum TokenType {
        ACCESS, REFRESH
    }

    @Value("${jwt.access.secret-key}")
    private String accessKey;

    @Value("${jwt.refresh.secret-key}")
    private String refreshKey;

    public String getAccessToken(String username) {
        return createToken(username, TokenType.ACCESS);
    }

    public String getRefreshToken(String username) {
        return createToken(username, TokenType.REFRESH);
    }

    public String getRefreshAccessToken(String refreshToken) {
        return createToken(getUsername(refreshToken, TokenType.REFRESH), TokenType.ACCESS);
    }

    public String getUsername(String token, TokenType type) {
        return getClaims(token, type).getPayload().get("sub", String.class);
    }

    public boolean isExpired(String accessToken) {
        try {
            Date expireTime = getExpireTime(accessToken, TokenType.ACCESS);
        }catch (ExpiredJwtException e){
            return true;
        }
        return false;
    }

    private Date getExpireTime(String token, TokenType tokenType) {
        Jws<Claims> jws = getClaims(token, tokenType);
        return jws.getPayload().get("exp", Date.class);
    }

    private Date getExpirePeriod(TokenType type) {
        int expireTime = 0;

        if (type == TokenType.ACCESS)
            expireTime = 1000 * 60 * 10; // 10분
        else
            expireTime = 1000 * 60 * 60 * 24 * 9999;

        return new Date(System.currentTimeMillis() + expireTime);
    }

    private SecretKey getMyKey(TokenType type) {

        String targetKey = null;
        if (type == TokenType.ACCESS) {
            targetKey = accessKey;
        } else if (type == TokenType.REFRESH) {
            targetKey = refreshKey;
        }
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(targetKey));
    }

    private Jws<Claims> getClaims(String token, TokenType type) throws SignatureException, ExpiredJwtException {
        Jws<Claims> jws = null;
        jws = Jwts.parser()
                .verifyWith(getMyKey(type))
                .build()
                .parseSignedClaims(token);
        return jws;
    }

    private String createToken(String username, TokenType type) {
        Date expireDate = getExpirePeriod(type);
        return Jwts.builder()
                .setSubject(username)
                .claim("sub", username)
                .claim("exp", expireDate)
                .signWith(getMyKey(type))
                .compact();
    }
}