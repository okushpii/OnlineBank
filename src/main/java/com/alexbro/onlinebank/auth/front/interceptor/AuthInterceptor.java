package com.alexbro.onlinebank.auth.front.interceptor;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

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
