package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.data.transfer.TransferRequestData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.Mapping.TRANSFER)
public class TransferPageController {

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public String getTransferPage(Model model, HttpServletRequest request) {
        Optional<AuthData> authData = Optional.ofNullable((AuthData) request.getSession().getAttribute(WebConstants.SessionAttributes.AUTH_DATA));
        authData.flatMap(ad -> userFacade.findByCode(ad.getUserCode()))
                .ifPresent(u -> model.addAttribute(WebConstants.ModelAttributes.ACCOUNTS, u.getAccounts()));
        if (model.getAttribute(WebConstants.ModelAttributes.TRANSFER_REQUEST_DATA) == null) {
            model.addAttribute(WebConstants.ModelAttributes.TRANSFER_REQUEST_DATA, new TransferRequestData());
        }
        return WebConstants.Pages.TRANSFER;
    }
}
