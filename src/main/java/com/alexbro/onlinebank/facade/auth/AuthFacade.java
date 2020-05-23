package com.alexbro.onlinebank.facade.auth;

import com.alexbro.onlinebank.facade.data.auth.AuthData;

public interface AuthFacade {

    AuthData authorize(String username, String password);

    boolean isAuthorized(AuthData authData);

}
