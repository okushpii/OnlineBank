package com.alexbro.onlinebank.facade.data.factory;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.facade.data.auth.AuthData;
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
