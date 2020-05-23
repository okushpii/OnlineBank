package com.alexbro.onlinebank.facade.converter.user;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserConverter extends AbstractConverter<User, UserData> {

    @Resource
    private Populator<User, UserData> userPopulator;

    @Override
    public UserData convert(User user) {
        UserData userData = new UserData();
        userPopulator.populate(user, userData);
        return userData;
    }
}
