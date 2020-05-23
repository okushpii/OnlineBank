package com.alexbro.onlinebank.config;

import com.alexbro.onlinebank.facade.data.auth.AuthData;
import com.alexbro.onlinebank.facade.auth.AuthFacade;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private AuthFacade authFacade;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        AuthData authData = (AuthData) request.getSession().getAttribute("authData");

        if (!authFacade.isAuthorized(authData)){
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
