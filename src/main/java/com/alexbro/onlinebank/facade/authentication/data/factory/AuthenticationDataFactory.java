package com.alexbro.onlinebank.facade.authentication.data.factory;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;

public interface AuthenticationDataFactory {

    AuthenticationData createAuthenticationData(User user, String token);
}
