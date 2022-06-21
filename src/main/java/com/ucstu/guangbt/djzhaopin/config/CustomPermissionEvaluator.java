package com.ucstu.guangbt.djzhaopin.config;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    // 检查用户是否具有权限
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    // 检查用户是否有权执行某项操作。
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        return true;
    }

}
