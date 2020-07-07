package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.currency.CurrencyFacade;
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
@RequestMapping(WebConstants.Mapping.EXCHANGE_STEP_ONE)
public class ExchangeStepOnePageController {

    @Resource
    private CurrencyFacade currencyFacade;

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public String getExchangeStepOnePage(HttpServletRequest request, Model model) {
        Optional<AuthData> authData = Optional.ofNullable((AuthData) request.getSession().getAttribute(WebConstants.SessionAttributes.AUTH_DATA));
        authData.flatMap(ad -> userFacade.getByCode(ad.getUserCode())).ifPresent(u -> model.
                addAttribute(WebConstants.RequestAttributes.CURRENCIES, currencyFacade.findAllByUser(u.getCode())));
        return WebConstants.Pages.EXCHANGE_STEP_ONE;
    }
}
