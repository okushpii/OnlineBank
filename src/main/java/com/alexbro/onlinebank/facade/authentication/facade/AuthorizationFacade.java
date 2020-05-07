package com.alexbro.onlinebank.facade.authentication.facade;

import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;

public interface AuthorizationFacade {

    AuthenticationData authorize(String username, String password);

    boolean isAuthorized(AuthenticationData authenticationData);

}
