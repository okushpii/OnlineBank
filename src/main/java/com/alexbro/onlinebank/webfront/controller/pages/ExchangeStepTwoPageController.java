package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.currency.CurrencyFacade;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.Mapping.EXCHANGE_STEP_TWO)
public class ExchangeStepTwoPageController {

    @Resource
    private AccountFacade accountFacade;
    @Resource
    private UserFacade userFacade;
    @Resource
    private CurrencyFacade currencyFacade;

    @Resource
    private I18Service i18Service;

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeStepTwoPageController.class);

    @GetMapping
    public String getExchangeStepTwoPage(@RequestParam String currencyFromCode,
                                         @RequestParam String currencyToCode, Model model, RedirectAttributes redirectAttributes,
                                         HttpServletRequest request) {
        Optional<AuthData> authData = Optional.ofNullable((AuthData) request.getSession().getAttribute(WebConstants.SessionAttributes.AUTH_DATA));
        authData.flatMap(ad -> userFacade.findByCode(ad.getUserCode())).ifPresent(u -> model.addAttribute(WebConstants.RequestAttributes.
                ACCOUNTS_FROM, accountFacade.findAllByCurrency(currencyFromCode)));
        authData.flatMap(ad -> userFacade.findByCode(ad.getUserCode())).ifPresent(u -> model.addAttribute(WebConstants.RequestAttributes.
                ACCOUNTS_TO, accountFacade.findAllByCurrency(currencyToCode)));
        try {
            addCurrencies(model, currencyFromCode, currencyToCode);
            return WebConstants.Pages.EXCHANGE_STEP_TWO;
        } catch (AccountsOperationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            LOG.info(e.getMessage(), e);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.EXCHANGE_STEP_ONE;
        }
    }

    private void addCurrencies(Model model, String currencyFromCode, String currencyToCode) {
        if (!currencyFromCode.equals(currencyToCode)) {
            model.addAttribute(WebConstants.RequestAttributes.CURRENCY_FROM, currencyFacade.findByCode(currencyFromCode).orElseThrow());
            model.addAttribute(WebConstants.RequestAttributes.CURRENCY_TO, currencyFacade.findByCode(currencyToCode).orElseThrow());
        } else
            throw new AccountsOperationException(i18Service.getLocalizedValue(WebConstants.Messages.CURRENCIES_MATCHES_EXCEPTION_MESSAGE));
    }
}
