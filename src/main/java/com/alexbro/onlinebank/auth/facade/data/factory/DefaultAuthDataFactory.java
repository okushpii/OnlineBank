package com.alexbro.onlinebank.auth.facade.data.factory;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.entity.Principal;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultAuthDataFactory implements AuthDataFactory {

    @Resource
    private AuthData authData;

    @Override
    public AuthData create(Principal principal, String token) {
        authData.setUsername(principal.getUsername());
        authData.setToken(token);
        authData.setUserCode(principal.getCode());
        authData.setRole(principal.getRole());
        return authData;
    }
}
