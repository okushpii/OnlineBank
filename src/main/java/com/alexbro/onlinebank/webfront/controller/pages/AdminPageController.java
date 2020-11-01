package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.employee.EmployeeFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(WebConstants.Mapping.ADMIN)
public class AdminPageController {

    @Resource
    private EmployeeFacade employeeFacade;

    @Resource
    private AuthManager authManager;

    @GetMapping("{code}")
    public String getAdminPage(@PathVariable String code, Model model, HttpSession session) {
        if (code.equals(authManager.getAuthData(session).getUserCode())) {
            model.addAttribute(WebConstants.ModelAttributes.ADMIN, employeeFacade.findByCode(code).orElseThrow());
            return WebConstants.Pages.ADMIN;
        } else {
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.LOGIN;
        }
    }
}
