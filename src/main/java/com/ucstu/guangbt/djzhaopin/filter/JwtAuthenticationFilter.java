package com.ucstu.guangbt.djzhaopin.filter;

import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        try {
            String jwtToken = request.getHeader("Authorization");
            if (StringUtils.hasText(jwtToken) && jwtToken.startsWith("Bearer ")) {
                jwtToken = jwtToken.substring(7, jwtToken.length());
            }
            if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
                UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken), "",
                        jwtTokenUtil.getRolesFromToken(jwtToken));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (ExpiredJwtException e) {
            ResponseBody<String> responseBody = new ResponseBody<>();
            responseBody.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseBody.setMessage("token过期");
            responseBody.setStatus(HttpStatus.UNAUTHORIZED.value());
            printWriter.write(new ObjectMapper().writeValueAsString(responseBody));
        } catch (BadCredentialsException e) {
            log.error("Request to parse invalid JWT : {} failed.", e.getMessage());
        }
        chain.doFilter(request, response);
    }

}
