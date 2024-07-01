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

    public String createToken(String username, TokenType type) {
        Date expireDate = getExpireTime(type);
        return Jwts.builder()
                .setSubject(username)
                .claim("sub", username)
                .claim("exp", expireDate)
                .signWith(getMyKey(type))
                .compact();
    }

    private Date getExpireTime(TokenType type) {
        int expireTime = 0;

        if (type == TokenType.ACCESS)
            expireTime = 1000 * 60 * 10; // 10분
        else
            expireTime = 1000 * 60 * 60 * 24 * 9999;

        return new Date(System.currentTimeMillis() + expireTime);
    }


    public boolean isExpire(String token, TokenType type) {
        Jws<Claims> jws = getClaims(token, type);
        Date expiration = jws.getPayload().get("exp", Date.class);

        if (expiration.before(new Date())) {
            return true;
        }

        return false;
    }

    public String getUsername(String token, TokenType type) {
        return getClaims(token, type).getPayload().get("sub", String.class);
    }

    public Jws<Claims> getClaims(String token, TokenType type) {
        Jws<Claims> jws = null;
        jws = Jwts.parser()
                .verifyWith(getMyKey(type))
                .build()
                .parseSignedClaims(token);
        return jws;
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
}
