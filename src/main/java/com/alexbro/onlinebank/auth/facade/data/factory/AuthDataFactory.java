package com.alexbro.onlinebank.auth.facade.data.factory;

import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.auth.facade.data.AuthData;

public interface AuthDataFactory {

    AuthData create(Principal principal, String token);
}
