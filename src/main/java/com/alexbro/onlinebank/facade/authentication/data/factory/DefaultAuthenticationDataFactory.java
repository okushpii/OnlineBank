package com.alexbro.onlinebank.facade.authentication.data.factory;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultAuthenticationDataFactory implements AuthenticationDataFactory {

    @Resource
    private AuthenticationData authenticationData;

    @Override
    public AuthenticationData createAuthenticationData(User user, String token) {
        authenticationData.setUsername(user.getUsername());
        authenticationData.setToken(token);
        return authenticationData;
    }
}
