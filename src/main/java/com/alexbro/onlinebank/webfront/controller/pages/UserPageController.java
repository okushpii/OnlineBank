package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(WebConstants.Mapping.USER)
public class UserPageController {

    @Resource
    private UserFacade userFacade;

    @GetMapping("/{userCode}")
    public String getUserPage(@PathVariable String userCode, Model model) {
        model.addAttribute(WebConstants.RequestAttributes.USER, userFacade.getByCode(userCode).orElseThrow());
        return WebConstants.Pages.USER;
    }
}
