package com.alexbro.onlinebank.controller;

import com.alexbro.onlinebank.core.exception.LoginException;
import com.alexbro.onlinebank.facade.data.auth.AuthorizationRequest;
import com.alexbro.onlinebank.facade.data.auth.AuthData;
import com.alexbro.onlinebank.facade.auth.AuthFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
    public class LoginPageController {

    @Resource
    private AuthFacade authFacade;

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("authorizationRequest", new AuthorizationRequest());
        return "pages/loginPage";
    }

    @PostMapping
    public String authorize(@ModelAttribute AuthorizationRequest authorizationRequest,
                            HttpServletRequest request, Model model) {
        try {
            AuthData authData = authFacade.authorize(authorizationRequest.getUsername(),
                    authorizationRequest.getPassword());
            request.getSession().setAttribute("authData", authData);
            return "redirect:/user/" + authData.getUserCode();
        }catch (LoginException e){
            String errorMessage = "User is not found by this login and password!";
            model.addAttribute("error" , errorMessage);
            return getLoginPage(model);
        }
    }
}
