package com.alexbro.onlinebank.webfront.controller;

import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeRequestData;
import com.alexbro.onlinebank.facade.data.transfer.TransferRequestData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final String ACCOUNT_TO_CODE = "a2";
    private static final String CURRENCY_FROM_CODE = "c1";
    private static final String CURRENCY_TO_CODE = "c2";
    private static final Long CARD_NUMBER = 512333L;
    private static final Double SUM = 100.0;
    private static final String USER_REDIRECT = "redirect:/user/";
    private static final String TRANSFER_REDIRECT = "redirect:/transfer";
    private static final String EXCHANGE_STEP_ONE_REDIRECT = "redirect:/exchangeStepOne";
    private static final String REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE = "redirect:/exchangeStepTwo";
    private static final String USER_CODE = "u1";
    private static final String ERROR_MESSAGE = "user.error.message";
    private static final String TRANSFER_BINDING_RESULT = "org.springframework.validation.BindingResult.transferRequestData";
    private static final String EXCHANGE_BINDING_RESULT = "org.springframework.validation.BindingResult.exchangeRequestData";
    private static final String TRANSFER_REQUEST_DATA = "transferRequestData";
    private static final String EXCHANGE_REQUEST_DATA = "exchangeRequestData";

    @InjectMocks
    private AccountController testedInstance;

    @Mock
    private AccountFacade accountFacade;
    @Mock
    private AuthData authData;
    @Mock
    private AuthManager authManager;
    @Mock
    private HttpSession session;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Mock
    private TransferRequestData transferRequestData;
    @Mock
    private ExchangeRequestData exchangeRequestData;
    @Mock
    private AccountData accountFrom;
    @Mock
    private AccountData accountTo;
    @Mock
    private CurrencyData currencyFrom;
    @Mock
    private CurrencyData currencyTo;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private UserFacade userFacade;
    @Mock
    private UserData userFrom;
    @Mock
    private UserData userTo;
    @Mock
    private AuthFacade authFacade;

    @Before
    public void setUp() {
        when(authManager.getAuthData(session)).thenReturn(authData);
        when(authData.getUserCode()).thenReturn(USER_CODE);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(transferRequestData.getAccountCode()).thenReturn(ACCOUNT_CODE);
        when(transferRequestData.getCardNumber()).thenReturn(String.valueOf(CARD_NUMBER));
        when(transferRequestData.getSum()).thenReturn(SUM);
        when(exchangeRequestData.getAccountFromCode()).thenReturn(ACCOUNT_CODE);
        when(exchangeRequestData.getAccountToCode()).thenReturn(ACCOUNT_TO_CODE);
        when(exchangeRequestData.getSum()).thenReturn(SUM);
        when(accountFacade.findByCode(ACCOUNT_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountFacade.findByCode(ACCOUNT_TO_CODE)).thenReturn(Optional.of(accountTo));
        when(accountFrom.getCurrency()).thenReturn(currencyFrom);
        when(accountTo.getCurrency()).thenReturn(currencyTo);
        when(currencyFrom.getCode()).thenReturn(CURRENCY_FROM_CODE);
        when(currencyTo.getCode()).thenReturn(CURRENCY_TO_CODE);
        when(userFacade.findByAccount(ACCOUNT_CODE)).thenReturn(Optional.of(userFrom));
        when(userFacade.findByAccount(ACCOUNT_TO_CODE)).thenReturn(Optional.of(userTo));
        when(userFrom.getCode()).thenReturn(USER_CODE);
    }

    @Test
    public void shouldTransfer() {
        String result = testedInstance.transfer(transferRequestData, bindingResult, session, redirectAttributes);

        verify(authFacade).isCurrentUser(USER_CODE, USER_CODE);
        verify(accountFacade).transfer(ACCOUNT_CODE, CARD_NUMBER, SUM);
        assertEquals(USER_REDIRECT + USER_CODE, result);
    }

    @Test
    public void shouldTransferWhenBindingResultHasError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = testedInstance.transfer(transferRequestData, bindingResult, session, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(TRANSFER_BINDING_RESULT, bindingResult);
        verify(redirectAttributes).addFlashAttribute(TRANSFER_REQUEST_DATA, transferRequestData);
        assertEquals(TRANSFER_REDIRECT, result);
    }

    @Test
    public void shouldTransferWhenThrowAccountsOperationException() {
        doThrow(new AccountsOperationException(ERROR_MESSAGE)).when(accountFacade).transfer(ACCOUNT_CODE, CARD_NUMBER,
                SUM);

        String result = testedInstance.transfer(transferRequestData, bindingResult, session, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(TRANSFER_REDIRECT, result);

    }

    @Test
    public void shouldTransferWhenThrowAuthException() {
        when(userFacade.findByAccount(ACCOUNT_CODE)).thenThrow(new AuthException(ERROR_MESSAGE));

        String result = testedInstance.transfer(transferRequestData, bindingResult, session, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(TRANSFER_REDIRECT, result);
    }

    @Test
    public void shouldExchange() {
        String result = testedInstance.exchange(exchangeRequestData, bindingResult, session, redirectAttributes);

        verify(authFacade).isCurrentUser(USER_CODE, USER_CODE);
        verify(accountFacade).exchange(ACCOUNT_CODE, ACCOUNT_TO_CODE, SUM);
        assertEquals(USER_REDIRECT + USER_CODE, result);
    }

    @Test
    public void shouldExchangeWhenThrowAccountsOperationException() {
        doThrow(new AccountsOperationException(ERROR_MESSAGE)).when(accountFacade).exchange(ACCOUNT_CODE, ACCOUNT_TO_CODE,
                SUM);

        String result = testedInstance.exchange(exchangeRequestData, bindingResult, session, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(EXCHANGE_STEP_ONE_REDIRECT, result);
    }

    @Test
    public void shouldExchangeWhenBindingResultHasError() {
        String expected = REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE + "?currencyFromCode=" + CURRENCY_FROM_CODE +
                "&currencyToCode=" + CURRENCY_TO_CODE;
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = testedInstance.exchange(exchangeRequestData, bindingResult, session, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(EXCHANGE_BINDING_RESULT, bindingResult);
        verify(redirectAttributes).addFlashAttribute(EXCHANGE_REQUEST_DATA, exchangeRequestData);
        assertEquals(expected, result);
    }

    @Test
    public void shouldExchangeWhenThrowAuthException() {
        when(userFacade.findByAccount(ACCOUNT_CODE)).thenThrow(new AuthException(ERROR_MESSAGE));

        String result = testedInstance.exchange(exchangeRequestData, bindingResult, session, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(EXCHANGE_STEP_ONE_REDIRECT, result);
    }
}
