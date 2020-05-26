package com.alexbro.onlinebank.storefront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.Mapping.HOME_PAGE_MAPPING)
public class HomePageController {

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest request) {
        Optional<AuthData> authData = Optional.ofNullable((AuthData) request.getSession().getAttribute(WebConstants.
                Attributes.AUTH_DATA_ATTRIBUTE));
        authData.ifPresent(ad -> userFacade.getByCode(ad.getUserCode())
                .ifPresent(u -> model.addAttribute(WebConstants.Attributes.USER_ATTRIBUTE, u)));
        return WebConstants.Pages.HOME_PAGE;
    }
}
