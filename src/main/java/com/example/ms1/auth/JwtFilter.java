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
import java.util.List;

public class JwtFilter implements Filter {

    private static final List<String> EXCLUDE_URLS = Arrays.asList(
//            "/api/v1/auth/success",
            "/api/v1/auth/fail",
//            "/api/v1/books",
//            "/api/v1/notes",
            "/api/v1/auth/login",
            "/test",
            "/test2"
    );


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("JwtFilter.doFilter");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String url = httpReq.getRequestURI();
        if (EXCLUDE_URLS.contains(httpReq.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        try {
            Cookie[] cookies = httpReq.getCookies();

            if (cookies == null) {
                System.out.println("쿠키가 없습니다.");
                chain.doFilter(request, response);
                return;
            }

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accessToken")) {
                    String token = cookie.getValue();
                    JwtUtil jwtUtil = new JwtUtil();

                    if (!jwtUtil.checkToken(token)) {
                        System.out.println("토큰이 만료되었습니다.");
                        httpResp.sendRedirect("/api/v1/auth/fail");
                        return;
                    }

                    Authentication authentication = new UsernamePasswordAuthenticationToken(jwtUtil.getUsername(token), null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("인증 실패");
            httpResp.sendRedirect("/api/v1/auth/fail");
        }

        System.out.println("AccessToken이 없습니다.");
        chain.doFilter(request, response);
    }

}
