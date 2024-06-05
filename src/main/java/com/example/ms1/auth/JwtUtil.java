package com.example.ms1.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private SecretKey key;
    JwtUtil() {
        this.key = Jwts.SIG.HS256.key().build();
    }
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key)
                .compact();
    }
}
