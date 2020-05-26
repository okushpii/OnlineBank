package com.alexbro.onlinebank.storefront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import com.alexbro.onlinebank.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(WebConstants.Mapping.LOGIN_PAGE_MAPPING)
    public class LoginPageController {

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute(WebConstants.Attributes.AUTH_REQUEST_ATTRIBUTE, new AuthRequest());
        return WebConstants.Pages.LOGIN_PAGE;
    }

}
