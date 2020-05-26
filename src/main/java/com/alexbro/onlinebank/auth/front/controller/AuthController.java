package com.alexbro.onlinebank.auth.front.controller;

import com.alexbro.onlinebank.auth.facade.AuthFacade;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import com.alexbro.onlinebank.auth.exception.AuthException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthFacade authFacade;


    @PostMapping
    public String authorize(@ModelAttribute AuthRequest authRequest,
                            HttpServletRequest request, Model model) {
        try {
            AuthData authData = authFacade.authorize(authRequest.getUsername(),
                    authRequest.getPassword());
            request.getSession().setAttribute("authData", authData);
            return "redirect:/user/" + authData.getUserCode();
        }catch (AuthException e){
            String errorMessage = "User is not found by this login and password!";
            model.addAttribute("error" , errorMessage);
            return "pages/loginPage";
        }
    }
}
