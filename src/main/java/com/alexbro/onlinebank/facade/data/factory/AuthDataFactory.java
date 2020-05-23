package com.alexbro.onlinebank.facade.data.factory;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.facade.data.auth.AuthData;

public interface AuthDataFactory {

    AuthData create(User user, String token);
}
