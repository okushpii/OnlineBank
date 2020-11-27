package com.alexbro.onlinebank.facade.user;

import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.facade.data.user.UserData;

import java.util.Optional;

public interface UserFacade {

    Optional<UserData> findByCode(String code);

    void register(RegisterData registerData);

    Optional<UserData> findByAccount(String accountCode);
}
