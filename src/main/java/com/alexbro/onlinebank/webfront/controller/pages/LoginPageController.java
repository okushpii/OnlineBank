package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(WebConstants.Mapping.LOGIN)
    public class LoginPageController {

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute(WebConstants.ModelAttributes.AUTH_REQUEST, new AuthRequest());
        return WebConstants.Pages.LOGIN;
    }

}
