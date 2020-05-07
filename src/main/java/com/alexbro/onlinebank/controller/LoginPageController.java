package com.alexbro.onlinebank.controller;

import com.alexbro.onlinebank.core.exception.LoginException;
import com.alexbro.onlinebank.facade.authentication.data.AuthorizationRequest;
import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;
import com.alexbro.onlinebank.facade.authentication.facade.AuthorizationFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
    public class LoginPageController {

    @Resource
    private AuthorizationFacade authorizationFacade;

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("authorizationRequest", new AuthorizationRequest());
        return "loginPage";
    }

    @PostMapping
    public String authorize(@ModelAttribute AuthorizationRequest authorizationRequest,
                            HttpServletRequest request, Model model) {
        try {
            AuthenticationData authenticationData = authorizationFacade.authorize(authorizationRequest.getUsername(),
                    authorizationRequest.getPassword());
            request.getSession().setAttribute("auth-data", authenticationData);
        }catch (LoginException e){
            String errorMessage = "User is not found by this login and password!";
            model.addAttribute("error" , errorMessage);
            return getLoginPage(model);
        }
        return "redirect:/user";
    }
}
