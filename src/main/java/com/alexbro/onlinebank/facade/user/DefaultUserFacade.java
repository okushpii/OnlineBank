package com.alexbro.onlinebank.facade.user;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.core.service.user.UserService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.user.UserData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DefaultUserFacade implements UserFacade {

    @Resource
    private UserService userService;

    @Resource
    private Converter<User, UserData> userConverter;

    @Override
    public Optional<UserData> findByCode(String code) {
        return userService.findByCode(code).map(u -> userConverter.convert(u));
    }
}
