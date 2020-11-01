package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(WebConstants.Mapping.USER)
public class UserPageController {

    @Resource
    private UserFacade userFacade;

    @Resource
    private AuthManager authManager;

    @GetMapping("/{userCode}")
    public String getUserPage(@PathVariable String userCode, Model model, HttpSession session) {
        if (userCode.equals(authManager.getAuthData(session).getUserCode())) {
            model.addAttribute(WebConstants.ModelAttributes.USER, userFacade.findByCode(userCode).orElseThrow());
            return WebConstants.Pages.USER;
        } else {
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.LOGIN;
        }
    }
}
