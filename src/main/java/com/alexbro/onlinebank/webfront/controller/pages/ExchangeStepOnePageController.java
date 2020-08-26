package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.currency.CurrencyFacade;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(WebConstants.Mapping.EXCHANGE_STEP_ONE)
public class ExchangeStepOnePageController {

    @Resource
    private CurrencyFacade currencyFacade;

    @Resource
    private UserFacade userFacade;

    @Resource
    private AuthManager authManager;

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeStepTwoPageController.class);

    @GetMapping
    public String getExchangeStepOnePage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        AuthData authData = authManager.getAuthData(session);
        try {
            userFacade.findByCode(authData.getUserCode()).ifPresent(u -> model.
                    addAttribute(WebConstants.ModelAttributes.CURRENCIES, currencyFacade.findAllByUser(u.getCode())));
            return WebConstants.Pages.EXCHANGE_STEP_ONE;
        } catch (AccountsOperationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            LOG.info(e.getMessage(), e);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.USER + "/" + authData.getUserCode();
        }
    }
}
