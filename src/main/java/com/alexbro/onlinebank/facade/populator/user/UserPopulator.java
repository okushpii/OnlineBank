package com.alexbro.onlinebank.facade.populator.user;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserPopulator implements Populator<User, UserData> {

    @Resource
    private Converter<Account, AccountData> accountsConverter;

    @Override
    public void populate(User user, UserData userData) {
        userData.setCode(user.getCode());
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setRole(user.getRole());
        userData.setAccounts(accountsConverter.convertAll(user.getAccounts()));
    }
}
