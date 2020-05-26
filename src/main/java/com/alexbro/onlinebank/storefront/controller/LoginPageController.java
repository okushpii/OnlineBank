package com.alexbro.onlinebank.storefront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
    public class LoginPageController {

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("authRequest", new AuthRequest());
        return "pages/loginPage";
    }

}
