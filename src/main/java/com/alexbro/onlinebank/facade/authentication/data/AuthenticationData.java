package com.alexbro.onlinebank.facade.authentication.data;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationData {

    private String username;

    private String token;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
