package com.alexbro.onlinebank.webfront.interceptor.validator;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class DefaultPrincipalRoleValidator implements PrincipalRoleValidator {

    @Resource
    private AuthManager authManager;

    @Override
    public boolean isRoleValid(HttpServletRequest request, Role role) {
        AuthData authData = authManager.getAuthData(request.getSession());
        return authData.getRole().equals(role);
    }
}
