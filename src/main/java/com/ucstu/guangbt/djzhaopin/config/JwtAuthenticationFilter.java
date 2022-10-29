package com.ucstu.guangbt.djzhaopin.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import com.ucstu.guangbt.djzhaopin.utils.ApplicationContextUtil;
import com.ucstu.guangbt.djzhaopin.utils.JsonWebTokenUtil;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private JsonWebTokenUtil jwtUtil;

    // 拦截所有请求并检查请求是否具有有效 JWT 令牌的过滤器。
    JwtAuthenticationFilter(RequestMatcher requiresAuth) {
        super(requiresAuth);
    }

    @Override
    // 请求处理。
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) {
        if (jwtUtil == null) { // 如果没有初始化(init函数)，则从 Spring 容器中获取。
            jwtUtil = ApplicationContextUtil.getBean(JsonWebTokenUtil.class);
        }
        String param = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION)).orElse("");
        String token = Optional.ofNullable(param).map(value -> StringUtils.delete(value, "Bearer "))
                .map(String::trim).orElseThrow(() -> new BadCredentialsException("No Token Found"));
        Authentication auth = new UsernamePasswordAuthenticationToken(token, token); // 创建认证信息。
        return getAuthenticationManager().authenticate(auth);
    }

    @Override
    // 请求处理完成后的操作。
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

}
