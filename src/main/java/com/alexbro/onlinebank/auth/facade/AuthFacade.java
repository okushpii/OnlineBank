package com.alexbro.onlinebank.auth.facade;

import com.alexbro.onlinebank.auth.facade.data.AuthData;

public interface AuthFacade {

    AuthData authorize(String username, String password);

    boolean isAuthorized(AuthData authData);
}
