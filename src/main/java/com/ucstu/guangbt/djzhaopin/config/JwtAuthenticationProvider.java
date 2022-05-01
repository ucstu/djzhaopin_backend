package com.ucstu.guangbt.djzhaopin.config;

import com.ucstu.guangbt.djzhaopin.utils.JwtUtil;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        final Object token = authentication.getCredentials();
        try {
            if (!jwtUtil.validateToken(String.valueOf(token))) {
                throw new AuthenticationException("Token Is Invalid") {
                };
            }
            UserDetails userDetails = jwtUtil.getUserDetailsFromToken(token.toString());
            if (userDetails == null) {
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
