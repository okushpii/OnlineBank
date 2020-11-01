package com.alexbro.onlinebank.webfront.interceptor;

import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.interceptor.validator.PrincipalRoleValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AdminRoleInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private PrincipalRoleValidator principalRoleValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (principalRoleValidator.isRoleValid(request, Role.EMPLOYEE)) {
            return super.preHandle(request, response, handler);
        }
        sendRedirect(response);
        return false;
    }

    private void sendRedirect(HttpServletResponse response) {
        try {
            response.sendRedirect(WebConstants.Mapping.LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
