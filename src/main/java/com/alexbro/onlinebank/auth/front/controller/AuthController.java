package com.alexbro.onlinebank.auth.front.controller;

import com.alexbro.onlinebank.auth.AuthConstants;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(WebConstants.Mapping.AUTH_MAPPING)
public class AuthController {

    @Resource
    private AuthFacade authFacade;

    @PostMapping
    public String authorize(@ModelAttribute AuthRequest authRequest,
                            HttpServletRequest request, Model model) {
        try {
            AuthData authData = authFacade.authorize(authRequest.getUsername(),
                    authRequest.getPassword());
            request.getSession().setAttribute(AuthConstants.AUTH_DATA_ATTRIBUTE, authData);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.USER_PAGE_MAPPING +  "/" + authData.getUserCode();
        }catch (AuthException e){
            model.addAttribute("error" , AuthConstants.ERROR_MESSAGE);
            return WebConstants.Pages.LOGIN_PAGE;
        }
    }
}
