package com.alexbro.onlinebank.facade.validation;

import com.alexbro.onlinebank.core.service.user.UserService;
import com.alexbro.onlinebank.facade.validation.costraint.UsernameUnique;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    @Resource
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByUsername(username).isEmpty();
    }
}
