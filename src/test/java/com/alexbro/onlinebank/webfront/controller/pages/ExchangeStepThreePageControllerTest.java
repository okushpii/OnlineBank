package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeRequestData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeStepThreePageControllerTest {

    private static final String USER_CODE = "u1";
    private static final String ACCOUNT_FROM_CODE = "a1";
    private static final String ACCOUNT_TO_CODE = "a2";
    private static final String CURRENCY_FROM_CODE = "c1";
    private static final String CURRENCY_TO_CODE = "c2";
    private static Double SUM = 100.0;
    private static final String EXCHANGE_STEP_THREE_PAGE = "pages/user/exchangeStepThreePage";
    private static final String REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE = "redirect:/exchangeStepTwo";
    private static final String EXCHANGE_BINDING_RESULT = "org.springframework.validation.BindingResult.exchangeRequestData";
    private static final String EXCHANGE_REQUEST_DATA = "exchangeRequestData";
    private static final String ERROR_MESSAGE = "error";

    @InjectMocks
    private ExchangeStepThreePageController testedInstance;

    @Mock
    private AccountFacade accountFacade;
    @Mock
    private UserFacade userFacade;
    @Mock
    private Model model;
    @Mock
    private AccountData accountFrom;
    @Mock
    private AccountData accountTo;
    @Mock
    private CurrencyData currencyFrom;
    @Mock
    private CurrencyData currencyTo;
    @Mock
    private ExchangeRequestData exchangeRequestData;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Mock
    private AuthManager authManager;
    @Mock
    private HttpSession session;
    @Mock
    private BindingResult bindingResult;

    private UserData userData;
    private AuthData authData;
    private ExchangeData exchangeData;

    @Before
    public void setUp() {
        authData = new AuthData();
        authData.setUserCode(USER_CODE);
        userData = new UserData();
        exchangeData = new ExchangeData();
        when(authManager.getAuthData(session)).thenReturn(authData);
        when(userFacade.findByCode(USER_CODE)).thenReturn(Optional.of(userData));
        when(accountFacade.getExchangeData(accountFrom, accountTo, SUM)).thenReturn(exchangeData);
        when(accountFacade.findByCode(ACCOUNT_FROM_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountFacade.findByCode(ACCOUNT_TO_CODE)).thenReturn(Optional.of(accountTo));
        when(exchangeRequestData.getAccountFromCode()).thenReturn(ACCOUNT_FROM_CODE);
        when(exchangeRequestData.getAccountToCode()).thenReturn(ACCOUNT_TO_CODE);
        when(exchangeRequestData.getSum()).thenReturn(SUM);
        when(accountFrom.getCurrency()).thenReturn(currencyFrom);
        when(accountTo.getCurrency()).thenReturn(currencyTo);
        when(currencyFrom.getCode()).thenReturn(CURRENCY_FROM_CODE);
        when(currencyTo.getCode()).thenReturn(CURRENCY_TO_CODE);
        when(bindingResult.hasErrors()).thenReturn(false);
    }

    @Test
    public void shouldGetExchangeStepThreePage() {
        String result = testedInstance.getExchangeStepThreePage(exchangeRequestData, bindingResult, model, redirectAttributes, session);

        verify(model).addAttribute("exchange", exchangeData);
        assertEquals(EXCHANGE_STEP_THREE_PAGE, result);
    }

    @Test
    public void shouldGetExchangeStepThreePageWhenThrowAccountsOperationException() {
        String expected = REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE + "?currencyFromCode=" + CURRENCY_FROM_CODE +
                "&currencyToCode=" + CURRENCY_TO_CODE;
        when(accountFacade.getExchangeData(accountFrom, accountTo, SUM)).thenThrow(new AccountsOperationException(ERROR_MESSAGE));

        String result = testedInstance.getExchangeStepThreePage(exchangeRequestData, bindingResult, model, redirectAttributes, session);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(expected, result);
    }

    @Test
    public void shouldGetExchangeStepThreePageWhenBindingResultHasError() {
        String expected = REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE + "?currencyFromCode=" + CURRENCY_FROM_CODE +
                "&currencyToCode=" + CURRENCY_TO_CODE;

        when(bindingResult.hasErrors()).thenReturn(true);

        String result = testedInstance.getExchangeStepThreePage(exchangeRequestData, bindingResult, model, redirectAttributes, session);

        verify(redirectAttributes).addFlashAttribute(EXCHANGE_BINDING_RESULT, bindingResult);
        verify(redirectAttributes).addFlashAttribute(EXCHANGE_REQUEST_DATA, exchangeRequestData);
        Assertions.assertEquals(expected, result);
    }
}
