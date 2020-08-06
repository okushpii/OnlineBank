package com.alexbro.onlinebank.webfront.controller;

import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.facade.validation.PasswordConfirmationValidator;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class UserController {

    @Resource
    private UserFacade userFacade;

    @Resource
    private PasswordConfirmationValidator passwordConfirmationValidator;


    @PostMapping(WebConstants.Mapping.REGISTRATION)
    public String register(@ModelAttribute @Valid RegisterData registerData, BindingResult bindingResult) {
        passwordConfirmationValidator.validate(registerData, bindingResult);
        if (bindingResult.hasErrors()) {
            return WebConstants.Pages.REGISTRATION;
        } else {
            userFacade.register(registerData);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.REGISTRATION;
        }
    }
}
