package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(WebConstants.Mapping.REGISTRATION)
public class RegistrationPageController {

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute(WebConstants.RequestAttributes.REGISTER, new RegisterData());
        return WebConstants.Pages.REGISTRATION;
    }
}
