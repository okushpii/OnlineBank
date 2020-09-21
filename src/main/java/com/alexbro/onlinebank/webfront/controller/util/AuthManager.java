package com.alexbro.onlinebank.webfront.controller.util;

import com.alexbro.onlinebank.auth.facade.data.AuthData;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface AuthManager {

    AuthData getAuthData(HttpSession session);

    Optional<AuthData> getOptionalAuthData(HttpSession session);
}
