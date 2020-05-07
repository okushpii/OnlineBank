package com.alexbro.onlinebank.config;

import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;
import com.alexbro.onlinebank.facade.authentication.facade.AuthorizationFacade;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private AuthorizationFacade authorizationFacade;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        AuthenticationData authData = (AuthenticationData) request.getSession().getAttribute("auth-data");

        if (!authorizationFacade.isAuthorized(authData)){
            sendRedirect(response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    private void sendRedirect(HttpServletResponse response){
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
