package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.data.transfer.TransferRequestData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(WebConstants.Mapping.TRANSFER)
public class TransferPageController {

    @Resource
    private UserFacade userFacade;

    @Resource
    private AuthManager authManager;

    @GetMapping
    public String getTransferPage(Model model, HttpSession session) {
        AuthData authData = authManager.getAuthData(session);
        userFacade.findByCode(authData.getUserCode()).
                ifPresent(u -> model.addAttribute(WebConstants.ModelAttributes.ACCOUNTS, u.getAccounts()));
        model.addAttribute(WebConstants.ModelAttributes.USER, userFacade.findByCode(authData.getUserCode()).orElseThrow());
        if (model.getAttribute(WebConstants.ModelAttributes.TRANSFER_REQUEST_DATA) == null) {
            model.addAttribute(WebConstants.ModelAttributes.TRANSFER_REQUEST_DATA, new TransferRequestData());
        }
        return WebConstants.Pages.TRANSFER;
    }
}
