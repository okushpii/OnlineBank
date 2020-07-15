package com.alexbro.onlinebank.webfront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping(WebConstants.Mapping.ACCOUNT)
public class AccountController {

    @Resource
    private AccountFacade accountFacade;

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("/transfer")
    public String transfer(@RequestParam String accountCode, @RequestParam Long cardNumber,
                           @RequestParam BigDecimal sum, HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {
        AuthData authData = (AuthData) request.getSession().
                getAttribute(WebConstants.SessionAttributes.AUTH_DATA);
        String userCode = authData.getUserCode();
        try {
            accountFacade.transfer(accountCode, cardNumber, sum);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.USER + "/" + userCode;
        } catch (AccountsOperationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            LOG.info(e.getMessage(), e);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.TRANSFER;
        }
    }
}
