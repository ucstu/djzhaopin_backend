package com.ucstu.guangbt.djzhaopin.config;

import java.io.Serializable;

import com.ucstu.guangbt.djzhaopin.model.JsonWebToken;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        JsonWebToken jsonWebToken = userDetails.getJsonWebToken();
        return true;
    }

}
