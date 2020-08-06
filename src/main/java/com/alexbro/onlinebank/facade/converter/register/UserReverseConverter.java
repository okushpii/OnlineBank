package com.alexbro.onlinebank.facade.converter.register;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserReverseConverter extends AbstractConverter<RegisterData, User> {

    @Resource
    private Populator<RegisterData, User> userReversePopulator;

    @Override
    public User convert(RegisterData registerData) {
        User user = new User();
        userReversePopulator.populate(registerData, user);
        return user;
    }
}
