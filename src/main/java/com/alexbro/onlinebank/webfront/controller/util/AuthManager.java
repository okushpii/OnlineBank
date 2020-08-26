package com.alexbro.onlinebank.webfront.controller.util;

import com.alexbro.onlinebank.auth.facade.data.AuthData;

import javax.servlet.http.HttpSession;

public interface AuthManager {

    AuthData getAuthData(HttpSession session);
}
