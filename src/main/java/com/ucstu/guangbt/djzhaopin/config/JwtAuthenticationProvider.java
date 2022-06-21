package com.ucstu.guangbt.djzhaopin.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ucstu.guangbt.djzhaopin.utils.JsonWebTokenUtil;

import jakarta.annotation.Resource;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Resource
    private JsonWebTokenUtil jwtUtil;

    @Override
    // 检查用户的密码。
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    // 检查用户是否存在。
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        Object token = authentication.getCredentials(); // 获取 JWT token。
        try {
            if (!jwtUtil.validateToken(String.valueOf(token))) { // 检查 token 是否有效。
                throw new AuthenticationException("Token Is Invalid") {
                };
            }
            CustomUserDetails userDetails = jwtUtil.getCustomUserDetailsFromToken(token.toString());
            if (userDetails == null) { // 检查用户是否存在。
                throw new AuthenticationException("User Not Found") {
                };
            }
            return userDetails;
        } catch (Exception e) {
            throw new AuthenticationException("Token Is Invalid") {
            };
        }
    }

}
