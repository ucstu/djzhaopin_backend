package com.ucstu.guangbt.djzhaopin.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ucstu.guangbt.djzhaopin.model.JsonWebToken;

import lombok.Data;

@Data
// 存储用户信息。
public class CustomUserDetails implements UserDetails {

    private String userName;
    private JsonWebToken jsonWebToken;

    public CustomUserDetails(String userName, JsonWebToken jsonWebToken) {
        this.userName = userName;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    // 获取用户权限。
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        List<String> _authorities = jsonWebToken.getAuthorities();
        for (String authority : _authorities) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    // 账户是否过期。
    public boolean isAccountNonExpired() {
        return !jsonWebToken.getExpired();
    }

    @Override
    // 账户是否被锁定。
    public boolean isAccountNonLocked() {
        return !jsonWebToken.getLocked();
    }

    @Override
    // 密码是否过期。
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // 账户是否可用。
    public boolean isEnabled() {
        return jsonWebToken.getEnabled();
    }

}
