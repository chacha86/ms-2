package com.example.ms1.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private SecretKey key;

    JwtUtil() {
        this.key = Jwts.SIG.HS256.key().build();
    }

    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("sub", username)
                .claim("exp", new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(key)
                .compact();
    }

    public boolean checkToken(String token) {
        Jws<Claims> jws = getClaims(token);
        Date expiration = jws.getPayload().get("exp", Date.class);

        if (expiration.before(new Date())) {
            return false;
        }

        return true;
    }

    public String getUsername(String token) {
        return getClaims(token).getPayload().get("sub", String.class);
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }
}
