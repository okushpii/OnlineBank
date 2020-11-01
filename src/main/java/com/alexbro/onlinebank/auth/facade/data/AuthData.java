package com.alexbro.onlinebank.auth.facade.data;

import com.alexbro.onlinebank.core.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class AuthData {

    private String username;
    private String token;
    private String userCode;
    private Role role;
    
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
