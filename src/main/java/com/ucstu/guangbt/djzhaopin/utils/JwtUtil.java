package com.ucstu.guangbt.djzhaopin.utils;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

    private String secret;
    private int jwtExpirationInMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String userName = userDetails.getUsername();
        Boolean isAccountNonExpired = userDetails.isAccountNonExpired();
        Boolean isAccountNonLocked = userDetails.isAccountNonLocked();
        Boolean isCredentialsNonExpired = userDetails.isCredentialsNonExpired();
        Boolean isEnabled = userDetails.isEnabled();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        claims.put("userName", userName);
        claims.put("isAccountNonExpired", isAccountNonExpired);
        claims.put("isAccountNonLocked", isAccountNonLocked);
        claims.put("isCredentialsNonExpired", isCredentialsNonExpired);
        claims.put("isEnabled", isEnabled);
        claims.put("authorities", authorities);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

    public Optional<String> getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Optional.ofNullable(claims.getSubject());
    }

    public UserDetails getUserDetailsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String userName = claims.getSubject();
        Boolean isAccountNonExpired = (Boolean) claims.get("isAccountNonExpired");
        Boolean isAccountNonLocked = (Boolean) claims.get("isAccountNonLocked");
        Boolean isCredentialsNonExpired = (Boolean) claims.get("isCredentialsNonExpired");
        Boolean isEnabled = (Boolean) claims.get("isEnabled");
        Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) claims
                .get("authorities");
        return new UserDetails() {
            @Override
            public String getPassword() {
                return "";
            }

            @Override
            public String getUsername() {
                return userName;
            }

            @Override
            public boolean isAccountNonExpired() {
                return isAccountNonExpired;
            }

            @Override
            public boolean isAccountNonLocked() {
                return isAccountNonLocked;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return isCredentialsNonExpired;
            }

            @Override
            public boolean isEnabled() {
                return isEnabled;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }
        };
    }
}
