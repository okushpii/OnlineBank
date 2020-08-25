package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeRequestData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.Mapping.EXCHANGE_STEP_THREE)
public class ExchangeStepThreePageController {

    @Resource
    private AccountFacade accountFacade;
    @Resource
    private UserFacade userFacade;

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeStepTwoPageController.class);

    @GetMapping
    public String getExchangeStepThreePage(@ModelAttribute @Valid ExchangeRequestData exchangeRequestData,
                                           BindingResult bindingResult,
                                           Model model,
                                           RedirectAttributes redirectAttributes,
                                           HttpServletRequest request) {

        Optional<AuthData> authData = Optional.ofNullable((AuthData) request.getSession().getAttribute(WebConstants.SessionAttributes.AUTH_DATA));
        AccountData accountFrom = accountFacade.findByCode(exchangeRequestData.getAccountFromCode()).orElseThrow();
        AccountData accountTo = accountFacade.findByCode(exchangeRequestData.getAccountToCode()).orElseThrow();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(WebConstants.ModelAttributes.EXCHANGE_BINDING_RESULT, bindingResult);
            redirectAttributes.addFlashAttribute(WebConstants.ModelAttributes.EXCHANGE_REQUEST_DATA, exchangeRequestData);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.EXCHANGE_STEP_TWO + "?currencyFromCode=" +
                    accountFrom.getCurrency().getCode() + "&currencyToCode=" +
                    accountTo.getCurrency().getCode();
        }
        try {
            ExchangeData exchangeData = accountFacade.getExchangeData(accountFrom, accountTo, exchangeRequestData.getSum());
            authData.flatMap(ad -> userFacade.findByCode(ad.getUserCode())).ifPresent(u -> model.
                    addAttribute(WebConstants.ModelAttributes.EXCHANGE, exchangeData));
            addExchangeRequestData(model);
            return WebConstants.Pages.EXCHANGE_STEP_THREE;
        } catch (AccountsOperationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            LOG.info(e.getMessage(), e);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.EXCHANGE_STEP_TWO + "?currencyFromCode=" +
                    accountFrom.getCurrency().getCode() + "&currencyToCode=" +
                    accountTo.getCurrency().getCode();
        }
    }

    private void addExchangeRequestData(Model model) {
        if (model.getAttribute(WebConstants.ModelAttributes.EXCHANGE_REQUEST_DATA) == null) {
            model.addAttribute(WebConstants.ModelAttributes.EXCHANGE_REQUEST_DATA, new ExchangeRequestData());
        }
    }
}
