package com.example.photography_api.interceptor;

import com.example.photography_api.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        String token = request.getHeader("Authorization");
        if (token == null || token.equals("")) {
            throw new RuntimeException("token不能为空");
        }
        Map<String, Object> map = JWTUtils.parseToken(token);
        Object acct = map.get("acct");
        if (acct != null) {
            return true;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
