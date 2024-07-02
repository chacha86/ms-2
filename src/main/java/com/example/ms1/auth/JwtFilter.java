package com.example.ms1.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private static final List<String> EXCLUDE_URLS = Arrays.asList(
//            "/api/v1/auth/success",
            "/api/v1/auth/fail",
//            "/api/v1/books",
//            "/api/v1/notes",
            "/api/v1/auth/login",
            "/test",
            "/test2",
            "/swagger-ui/index.html",
            "/swagger-ui.html"
    );


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("JwtFilter.doFilter");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String accessToken = null;
        String url = httpReq.getRequestURI();
        if (EXCLUDE_URLS.contains(httpReq.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

//        String authorization = ((HttpServletRequest) request).getHeader("Authorization");
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            accessToken = authorization.substring(7);
//        }
//        else {
            accessToken = getTokenOrNull(httpReq, "accessToken");
//        }

        if (accessToken == null) {
            System.out.println("AccessToken이 없습니다.");
            chain.doFilter(request, response);
            return;
        }

        if (jwtUtil.isExpired(accessToken)) {
            System.out.println("AccessToken이 만료되었습니다. 재갱신 합니다.");
            String refreshToken = getTokenOrNull(httpReq, "refreshToken");

            if(refreshToken == null){
                System.out.println("RefreshToken이 없습니다.");
                chain.doFilter(request, response);
                return;
            }

            accessToken = jwtUtil.getRefreshAccessToken(refreshToken);
            setToken(httpResp, accessToken);
        }

        authenticate(accessToken);
        chain.doFilter(request, response);
    }

    private String getTokenOrNull(HttpServletRequest httpReq, String tokenName) {

        Cookie[] cookies = httpReq.getCookies();

        if (cookies == null) {
            System.out.println("쿠키가 없습니다.");
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(tokenName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void setToken(HttpServletResponse httpResp, String accessToken) {
        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        httpResp.addCookie(cookie);
    }

    private void authenticate(String accessToken) {
        String username = jwtUtil.getUsername(accessToken, JwtUtil.TokenType.ACCESS);
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
