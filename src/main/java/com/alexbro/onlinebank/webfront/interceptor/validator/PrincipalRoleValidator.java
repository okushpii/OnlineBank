package com.alexbro.onlinebank.webfront.interceptor.validator;

import com.alexbro.onlinebank.core.entity.Role;

import javax.servlet.http.HttpServletRequest;

public interface PrincipalRoleValidator {

    boolean isRoleValid(HttpServletRequest request, Role role);
}
