package com.alexbro.onlinebank.webfront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeRequestData;
import com.alexbro.onlinebank.facade.data.transfer.TransferRequestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    private static final String AUTH_DATA_ATTRIBUTE = "authData";
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
    private HttpServletRequest request;
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

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AUTH_DATA_ATTRIBUTE)).thenReturn(authData);
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
    }

    @Test
    public void shouldTransfer() {
        String result = testedInstance.transfer(transferRequestData, bindingResult, request, redirectAttributes);

        verify(accountFacade).transfer(ACCOUNT_CODE, CARD_NUMBER, SUM);
        assertEquals(USER_REDIRECT + USER_CODE, result);
    }

    @Test
    public void shouldTransferWhenBindingResultHasError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = testedInstance.transfer(transferRequestData, bindingResult, request, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(TRANSFER_BINDING_RESULT, bindingResult);
        verify(redirectAttributes).addFlashAttribute(TRANSFER_REQUEST_DATA, transferRequestData);
        assertEquals(TRANSFER_REDIRECT, result);
    }

    @Test
    public void shouldReturnError() {
        doThrow(new AccountsOperationException(ERROR_MESSAGE)).when(accountFacade).transfer(ACCOUNT_CODE, CARD_NUMBER,
                SUM);

        String result = testedInstance.transfer(transferRequestData, bindingResult, request, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(TRANSFER_REDIRECT, result);

    }

    @Test
    public void shouldExchange() {
        String result = testedInstance.exchange(exchangeRequestData, bindingResult, request, redirectAttributes);

        verify(accountFacade).exchange(ACCOUNT_CODE, ACCOUNT_TO_CODE, SUM);
        assertEquals(USER_REDIRECT + USER_CODE, result);
    }

    @Test
    public void shouldExchangeWhenThrowAccountsOperationException() {
        doThrow(new AccountsOperationException(ERROR_MESSAGE)).when(accountFacade).exchange(ACCOUNT_CODE, ACCOUNT_TO_CODE,
                SUM);

        String result = testedInstance.exchange(exchangeRequestData, bindingResult, request, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(EXCHANGE_STEP_ONE_REDIRECT, result);
    }

    @Test
    public void shouldExchangeWhenBindingResultHasError(){
        String expected = REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE + "?currencyFromCode=" + CURRENCY_FROM_CODE +
                "&currencyToCode=" + CURRENCY_TO_CODE;
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = testedInstance.exchange(exchangeRequestData, bindingResult, request, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute(EXCHANGE_BINDING_RESULT, bindingResult);
        verify(redirectAttributes).addFlashAttribute(EXCHANGE_REQUEST_DATA, exchangeRequestData);
        assertEquals(expected, result);
    }
}
