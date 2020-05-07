package com.alexbro.onlinebank.core.service.authentication;

import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Override
    public boolean passwordMatches(String userPassword, String logUserPassword) {
        return userPassword.equals(logUserPassword);
    }
}
