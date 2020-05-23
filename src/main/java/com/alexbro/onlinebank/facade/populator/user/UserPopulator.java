package com.alexbro.onlinebank.facade.populator.user;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator implements Populator<User, UserData> {

    @Override
    public void populate(User user, UserData userData) {
        userData.setCode(user.getCode());
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setAccounts(user.getAccounts());
    }
}
