package com.example.ms1.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("JwtFilter.doFilter");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        try {
            Cookie[] cookies = httpReq.getCookies();
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("accessToken")) {
                    httpResp.sendRedirect("/api/v1/auth/success");

                    Authentication authentication = new UsernamePasswordAuthenticationToken("user", null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                }
            }
        } catch (Exception e) {
            System.out.println("accessToken이 없습니다.");
        }

        httpResp.sendRedirect("/api/v1/auth/fail");
    }
}
