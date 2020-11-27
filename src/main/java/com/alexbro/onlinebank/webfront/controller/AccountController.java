package com.alexbro.onlinebank.webfront.controller;

import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeRequestData;
import com.alexbro.onlinebank.facade.data.transfer.TransferRequestData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.WebConstants;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(WebConstants.Mapping.ACCOUNT)
public class AccountController {

    @Resource
    private AccountFacade accountFacade;
    @Resource
    private UserFacade userFacade;
    @Resource
    private AuthFacade authFacade;
    @Resource
    private AuthManager authManager;
    @Resource
    private I18Service i18Service;

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(WebConstants.Mapping.TRANSFER)
    public String transfer(@ModelAttribute(WebConstants.ModelAttributes.TRANSFER_REQUEST_DATA) @Valid TransferRequestData transferRequestData,
                           BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        AuthData authData = authManager.getAuthData(session);
        String userCode = authData.getUserCode();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(WebConstants.ModelAttributes.TRANSFER_BINDING_RESULT, bindingResult);
            redirectAttributes.addFlashAttribute(WebConstants.ModelAttributes.TRANSFER_REQUEST_DATA, transferRequestData);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.TRANSFER;
        }
        try {
            UserData user = userFacade.findByAccount(transferRequestData.getAccountCode()).
                    orElseThrow(() -> new AuthException(i18Service.getLocalizedValue(WebConstants.Messages.ACCOUNT_IS_NOT_FOUND_MESSAGE)));
            authFacade.isCurrentUser(authData.getUserCode(), user.getCode());
            accountFacade.transfer(transferRequestData.getAccountCode(),
                    Long.valueOf(transferRequestData.getCardNumber()), transferRequestData.getSum());
        } catch (AccountsOperationException | AuthException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            LOG.info(e.getMessage(), e);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.TRANSFER;
        }
        return WebConstants.Util.REDIRECT + WebConstants.Mapping.USER + "/" + userCode;
    }

    @PostMapping(WebConstants.Mapping.EXCHANGE)
    public String exchange(@ModelAttribute @Valid ExchangeRequestData exchangeRequestData,
                           BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        AuthData authData = authManager.getAuthData(session);
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
            UserData userFrom = userFacade.findByAccount(exchangeRequestData.getAccountFromCode()).
                    orElseThrow(() -> new AuthException(i18Service.getLocalizedValue(WebConstants.Messages.ACCOUNT_IS_NOT_FOUND_MESSAGE)));
            UserData userTo = userFacade.findByAccount(exchangeRequestData.getAccountToCode()).orElseThrow(() -> new AuthException(WebConstants.
                    Messages.ACCOUNT_IS_NOT_FOUND_MESSAGE));
            String userCode = authData.getUserCode();
            authFacade.isCurrentUser(authData.getUserCode(), userFrom.getCode());
            authFacade.isCurrentUser(authData.getUserCode(), userTo.getCode());
            accountFacade.exchange(exchangeRequestData.getAccountFromCode(), exchangeRequestData.getAccountToCode(), exchangeRequestData.getSum());
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.USER + "/" + userCode;
        } catch (AccountsOperationException | AuthException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            LOG.info(e.getMessage(), e);
            return WebConstants.Util.REDIRECT + WebConstants.Mapping.EXCHANGE_STEP_ONE;
        }
    }
}
