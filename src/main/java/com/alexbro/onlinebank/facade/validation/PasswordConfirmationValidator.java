package com.alexbro.onlinebank.facade.validation;

import com.alexbro.onlinebank.facade.data.register.RegisterData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordConfirmationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterData registerData = (RegisterData) o;
        if (!registerData.getPasswordConfirmation().equals(registerData.getPassword())) {
            errors.rejectValue("passwordConfirmation", ValidationConstants.Messages.PASSWORD_NOT_MATCHES);
        }
    }
}
