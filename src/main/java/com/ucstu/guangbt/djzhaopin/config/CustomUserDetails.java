package com.ucstu.guangbt.djzhaopin.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.ucstu.guangbt.djzhaopin.model.JsonWebToken;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

    private String userName;
    private JsonWebToken jsonWebToken;

    public CustomUserDetails(String userName, JsonWebToken jsonWebToken) {
        this.userName = userName;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
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
    public boolean isAccountNonExpired() {
        return !jsonWebToken.getExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !jsonWebToken.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return jsonWebToken.getEnabled();
    }

}
