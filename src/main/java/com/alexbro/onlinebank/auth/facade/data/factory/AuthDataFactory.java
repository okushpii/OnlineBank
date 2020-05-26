package com.alexbro.onlinebank.auth.facade.data.factory;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.auth.facade.data.AuthData;

public interface AuthDataFactory {

    AuthData create(User user, String token);
}
