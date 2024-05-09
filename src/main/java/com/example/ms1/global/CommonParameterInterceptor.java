package com.example.ms1.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonParameterInterceptor implements HandlerInterceptor {
    private final String DEFAULT_SORT = "title";
    private final String DEFAULT_KEYWORD = "";
    private final boolean DEFAULT_IS_SEARCH = false;
    private final boolean DEFAULT_IS_TAG_MODAL = false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String keyword = request.getParameter("keyword") == null ? DEFAULT_KEYWORD : (String)request.getParameter("keyword");

        String isSearch = request.getParameter("isSearch");
        String isTagModal = request.getParameter("isTagModal");
        String sort = request.getParameter("sort");
        request.setAttribute("test", "123");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
