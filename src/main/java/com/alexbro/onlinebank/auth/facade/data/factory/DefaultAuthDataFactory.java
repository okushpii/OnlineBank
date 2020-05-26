package com.alexbro.onlinebank.auth.facade.data.factory;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultAuthDataFactory implements AuthDataFactory {

    @Resource
    private AuthData authData;

    @Override
    public AuthData create(User user, String token) {
        authData.setUsername(user.getUsername());
        authData.setToken(token);
        authData.setUserCode(user.getCode());
        return authData;
    }
}
