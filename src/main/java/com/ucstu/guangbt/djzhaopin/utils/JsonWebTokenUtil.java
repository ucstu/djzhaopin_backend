package com.ucstu.guangbt.djzhaopin.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.config.CustomUserDetails;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.JsonWebToken;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
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
public class JsonWebTokenUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationDateInMs}")
    private int jwtExpirationInMs;

    public String generateToken(AccountInformation accountInformation) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountInformationId", accountInformation.getAccountInformationId());
        claims.put("fullInformationId", accountInformation.getFullInformationId());
        if (accountInformation.getAccountType() == 2) {
            claims.put("companyInformationId", accountInformation.getHrInformation().getCompanyInformationId());
        }
        claims.put("accountType", accountInformation.getAccountType());
        List<String> authorities = new ArrayList<>();
        accountInformation.getAuthorities().forEach(authority -> authorities.add(authority.getAuthorityName()));
        accountInformation.getGroups().forEach(
                group -> group.getAuthorities().forEach(authority -> authorities.add(authority.getAuthorityName())));
        claims.put("authorities", authorities);
        claims.put("expired", accountInformation.getExpired());
        claims.put("locked", accountInformation.getLocked());
        claims.put("enabled", accountInformation.getEnabled());
        return doGenerateToken(claims, accountInformation.getUserName());
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
            throw new BadCredentialsException("Invalid Token", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public JsonWebToken getJsonWebTokenFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return new JsonWebToken(
                UUID.fromString(claims.get("accountInformationId", String.class)),
                claims.get("accountType", Integer.class) == 2
                        ? claims.get("companyInformationId",
                                String.class) != null
                                        ? UUID.fromString(claims.get("companyInformationId", String.class))
                                        : null
                        : null,
                UUID.fromString(claims.get("fullInformationId", String.class)),
                claims.get("accountType", Integer.class),
                claims.get("authorities", List.class),
                claims.get("expired", Boolean.class),
                claims.get("locked", Boolean.class),
                claims.get("enabled", Boolean.class));
    }

    public UserDetails getUserDetailsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        JsonWebToken jsonWebToken = getJsonWebTokenFromToken(token);
        return new CustomUserDetails(claims.getSubject(), jsonWebToken);
    }
}
