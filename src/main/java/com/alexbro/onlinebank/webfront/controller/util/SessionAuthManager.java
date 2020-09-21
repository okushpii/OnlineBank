package com.alexbro.onlinebank.webfront.controller.util;

import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class SessionAuthManager implements AuthManager {

    @Override
    public AuthData getAuthData(HttpSession session) {
        Optional<AuthData> authData = Optional.ofNullable((AuthData)session.getAttribute(WebConstants.SessionAttributes.AUTH_DATA));
        return authData.orElseThrow(() -> new AuthException(WebConstants.Messages.AUTH_NOT_FOUND_EXCEPTION));
    }

    @Override
    public Optional<AuthData> getOptionalAuthData(HttpSession session){
        return Optional.ofNullable((AuthData)session.getAttribute(WebConstants.SessionAttributes.AUTH_DATA));
    }
}
