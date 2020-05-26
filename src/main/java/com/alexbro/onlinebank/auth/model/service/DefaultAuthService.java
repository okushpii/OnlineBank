package com.alexbro.onlinebank.auth.model.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultAuthService implements AuthService {

    @Override
    public boolean passwordMatches(String userPassword, String logUserPassword) {
        return userPassword.equals(logUserPassword);
    }
}
