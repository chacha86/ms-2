package com.example.ms1.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/refresh-access-token")
    public String refreshAccessToken(HttpServletResponse res) {
        String refreshToken = jwtUtil.createToken("chacha", JwtUtil.TokenType.REFRESH);
        String username = jwtUtil.getUsername(refreshToken, JwtUtil.TokenType.REFRESH);
        String token = jwtUtil.createToken(username, JwtUtil.TokenType.ACCESS);

        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        res.addCookie(cookie);

        return "{\"result\" : \"success\", \"token\" : \"" + token + "\", \"refreshToken\" : \"" + refreshToken + "\"}";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> requestParam, HttpServletResponse res) {
        System.out.println("hohhohoh");
        if (Boolean.valueOf((String) requestParam.get("flag")) == true) {
            String token = jwtUtil.createToken("chacha", JwtUtil.TokenType.ACCESS);
            Cookie cookie = new Cookie("accessToken", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            res.addCookie(cookie);

            String refreshToken = jwtUtil.createToken("chacha", JwtUtil.TokenType.REFRESH);
            Cookie cookie2 = new Cookie("refreshToken", refreshToken);
            cookie2.setHttpOnly(true);
            cookie2.setPath("/");
            res.addCookie(cookie2);

            return "{\"result\" : \"success\", \"token\" : \"" + token + "\", \"refreshToken\" : \"" + refreshToken + "\"}";
        } else
            return "{\"result\" : \"fail\"}";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        res.addCookie(cookie);

        return "{\"result\" : \"success\"}";
    }

    @PostMapping("/check")
    public String check() {
        return "{\"result\" : \"success\"}";
    }

    @GetMapping("/success")
    public ResultData login(String id, String password) {
        System.out.println("success");
        return new ResultData("S-200", "로그인 성공", "success");

    }

    @GetMapping("/fail")
    public ResultData fail() {
        System.out.println("fail");
        return new ResultData("F-401", "인증 실패12", "fail");
    }

    @GetMapping("/user")
    public ResultData user(Authentication authentication) {
        System.out.println(authentication);
        if (authentication == null) {
            System.out.println("null");
            return new ResultData("F-401", "로그인 실패", "fail");
        }
        System.out.println("user");
        return new ResultData("S-200", "유저 정보", "user");
    }
}
