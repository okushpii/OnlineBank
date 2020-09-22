package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.Mapping.HOME)
public class HomePageController {

    @Resource
    private UserFacade userFacade;
    @Resource
    private AuthManager authManager;

    @GetMapping
    public String getHomePage(Model model, HttpSession session) {
        Optional<AuthData> authData = authManager.getOptionalAuthData(session);
        authData.flatMap(ad -> userFacade.findByCode(ad.getUserCode()))
                .ifPresent(u -> model.addAttribute(WebConstants.ModelAttributes.USER, u));
        return WebConstants.Pages.HOME;
    }
}
