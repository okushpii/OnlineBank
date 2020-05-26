package com.alexbro.onlinebank.storefront.controller;

import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(WebConstants.Mapping.USER_PAGE_MAPPING)
public class UserPageController {

    @Resource
    private UserFacade userFacade;

    @GetMapping("/{userCode}")
    public String getUserPage(@PathVariable String userCode, Model model) {
        model.addAttribute(WebConstants.Attributes.USER_ATTRIBUTE, userFacade.getByCode(userCode).orElseThrow());
        return WebConstants.Pages.USER_PAGE;
    }
}
