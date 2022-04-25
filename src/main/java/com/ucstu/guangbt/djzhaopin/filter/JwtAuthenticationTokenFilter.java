package com.ucstu.guangbt.djzhaopin.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.repository.AccountInformationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private AccountInformationRepository accountInformationRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.substring(7);
        try {
            Jws<Claims> claim = Jwts.parser().setSigningKey("djzhaopin123456").parseClaimsJws(token);
            String accountId = claim.getBody().getSubject();
            AccountInformation accountInformation = accountInformationRepository.findById(UUID.fromString(accountId))
                    .get();
            Set<AccountAuthority> accountAuthorities = accountInformation.getAuthorities();
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (AccountAuthority accountAuthority : accountAuthorities) {
                authorities.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return accountAuthority.getAuthorityName();
                    }
                });
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    accountInformation, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>();
            ObjectMapper objectMapper = new ObjectMapper();
            responseBody.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseBody.setMessage("token过期");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        }
    }

}
