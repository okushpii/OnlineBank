package com.alexbro.onlinebank.facade.populator.register;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.core.service.id.IdGenerationService;
import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserReversePopulator implements Populator<RegisterData, User> {

    @Resource
    private IdGenerationService idGenerationService;

    @Resource
    private EncodePasswordService encodePasswordService;

    @Override
    public void populate(RegisterData registerData, User user) {
        user.setCode(idGenerationService.generate());
        user.setName(registerData.getName());
        user.setEmaill(registerData.getEmail());
        user.setUsername(registerData.getUsername());
        user.setPassword(encodePasswordService.encodePassword(registerData.getPassword()));
    }
}
