package com.alexbro.onlinebank.storefront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomePageController {

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest request) {
        Optional<AuthData> authData = Optional.ofNullable((AuthData) request.getSession().getAttribute("authData"));
        authData.ifPresent(ad -> userFacade.getByCode(ad.getUserCode())
                .ifPresent(u -> model.addAttribute("user", u)));
        return "pages/homePage";
    }
}
