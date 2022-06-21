package com.ucstu.guangbt.djzhaopin.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.ucstu.guangbt.djzhaopin.config.CustomUserDetails;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.model.JsonWebToken;

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
    private String secret; // 密钥
    @Value("${jwt.expirationDateInMs}")
    private int jwtExpirationInMs; // jwt过期时间

    /**
     * 我们创建一个Claims的map，将账户信息id、全信息id、公司信息id、账户类型、权限、过期、锁定、启用放入map中，
     * 然后调用doGenerateToken函数生成token
     */
    public String generateToken(AccountInformation accountInformation) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountInformationId", accountInformation.getAccountInformationId());
        if (accountInformation.getAccountType() == 1 || accountInformation.getAccountType() == 2) {
            claims.put("fullInformationId", accountInformation.getFullInformationId());
        }
        // 检查帐户类型是否为 2，即 HR，如果是，则获取公司信息 id 并将其放入索赔映射中。
        if (accountInformation.getAccountType() == 2) {
            Optional<CompanyInformation> companyInformationOptional = Optional
                    .ofNullable(
                            accountInformation.getHrInformation().getCompanyInformation());
            if (companyInformationOptional.isPresent()) {
                claims.put("companyInformationId", companyInformationOptional.get().getCompanyInformationId());
            }
        }
        claims.put("accountType", accountInformation.getAccountType());
        List<String> authorities = new ArrayList<>();
        // 从 accountInformation 和组中获取权限。
        accountInformation.getAuthorities().forEach(authority -> authorities.add(authority.getAuthorityName()));
        accountInformation.getGroups().forEach(
                group -> group.getAuthorities().forEach(authority -> authorities.add(authority.getAuthorityName())));
        claims.put("authorities", authorities); // 权限
        claims.put("expired", accountInformation.getExpired()); // 是否过期
        claims.put("locked", accountInformation.getLocked()); // 是否锁定
        claims.put("enabled", accountInformation.getEnabled()); // 是否启用
        return doGenerateToken(claims, accountInformation.getUserName());
    }

    // 生成token。
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 验证token。
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

    // 从token中获取用户名的方法。
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * 从token中获取用户名。
     */
    public JsonWebToken getJsonWebTokenFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return new JsonWebToken(
                UUID.fromString(claims.get("accountInformationId", String.class)),
                claims.get("accountType", Integer.class) == 1 || claims.get("accountType", Integer.class) == 2
                        ? claims.get("fullInformationId", String.class) != null
                                ? UUID.fromString(claims.get("fullInformationId", String.class))
                                : null
                        : null,
                claims.get("accountType", Integer.class) == 2
                        ? claims.get("companyInformationId",
                                String.class) != null
                                        ? UUID.fromString(claims.get("companyInformationId", String.class))
                                        : null
                        : null,
                claims.get("accountType", Integer.class),
                claims.get("authorities", List.class),
                claims.get("expired", Boolean.class),
                claims.get("locked", Boolean.class),
                claims.get("enabled", Boolean.class));
    }

    /**
     * > 它接受一个token，对其进行解析，并返回一个 CustomUserDetails 对象
     */
    public CustomUserDetails getCustomUserDetailsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        JsonWebToken jsonWebToken = getJsonWebTokenFromToken(token);
        return new CustomUserDetails(claims.getSubject(), jsonWebToken);
    }
}
