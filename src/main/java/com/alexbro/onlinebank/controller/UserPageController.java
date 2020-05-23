package com.alexbro.onlinebank.controller;

import com.alexbro.onlinebank.facade.user.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @Resource
    private UserFacade userFacade;

    @GetMapping("/{userCode}")
    public String getUserPage(@PathVariable String userCode, Model model) {
        model.addAttribute("user", userFacade.getByCode(userCode).orElseThrow());
        return "pages/userPage";
    }
}
