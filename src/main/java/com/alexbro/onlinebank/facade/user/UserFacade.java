package com.alexbro.onlinebank.facade.user;

import com.alexbro.onlinebank.facade.data.user.UserData;

import java.util.Optional;

public interface UserFacade {

    Optional<UserData> getByCode(String code);
}
