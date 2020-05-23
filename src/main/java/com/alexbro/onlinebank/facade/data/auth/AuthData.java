package com.alexbro.onlinebank.facade.data.auth;

import org.springframework.stereotype.Component;

@Component
public class AuthData {

    private String username;
    private String token;
    private String userCode;
    
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
