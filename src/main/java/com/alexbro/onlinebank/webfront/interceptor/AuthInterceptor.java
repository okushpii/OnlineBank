package com.alexbro.onlinebank.webfront.interceptor;

import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.auth.AuthConstants;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private AuthFacade authFacade;

    @Transactional
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        AuthData authData = (AuthData) request.getSession().getAttribute(WebConstants.SessionAttributes.AUTH_DATA);

        if (!authFacade.isAuthorized(authData)){
            sendRedirect(response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    private void sendRedirect(HttpServletResponse response){
        try {
            response.sendRedirect(WebConstants.Mapping.LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
