package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
    private static BigDecimal SUM = BigDecimal.valueOf(100);
    private static final String EXCHANGE_STEP_THREE_PAGE = "pages/exchangeStepThreePage";
    private static final String REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE = "redirect:/exchangeStepTwo";
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
    private RedirectAttributes redirectAttributes;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    private UserData userData;
    private AuthData authData;
    private ExchangeData exchangeData;

    @Before
    public void setUp() {
        authData = new AuthData();
        authData.setUserCode(USER_CODE);
        userData = new UserData();
        exchangeData = new ExchangeData();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("authData")).thenReturn(authData);
        when(userFacade.findByCode(USER_CODE)).thenReturn(Optional.of(userData));
        when(accountFacade.getExchangeData(accountFrom, accountTo, SUM)).thenReturn(exchangeData);
        when(accountFacade.findByCode(ACCOUNT_FROM_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountFacade.findByCode(ACCOUNT_TO_CODE)).thenReturn(Optional.of(accountTo));
        when(accountFrom.getCurrency()).thenReturn(currencyFrom);
        when(accountTo.getCurrency()).thenReturn(currencyTo);
        when(currencyFrom.getCode()).thenReturn(CURRENCY_FROM_CODE);
        when(currencyTo.getCode()).thenReturn(CURRENCY_TO_CODE);
    }

    @Test
    public void shouldGetExchangeStepThreePage() {
        String result = testedInstance.getExchangeStepThreePage(ACCOUNT_FROM_CODE, ACCOUNT_TO_CODE, SUM, model, redirectAttributes, request);

        verify(model).addAttribute("exchange", exchangeData);
        assertEquals(EXCHANGE_STEP_THREE_PAGE, result);
    }

    @Test
    public void shouldGetExchangeStepThreePageWhenAuthDataIsAbsent(){
        when(session.getAttribute("authData")).thenReturn(null);

        testedInstance.getExchangeStepThreePage(ACCOUNT_FROM_CODE, ACCOUNT_TO_CODE, SUM, model, redirectAttributes, request);

        verify(model, never()).addAttribute("exchange", exchangeData);
    }

    @Test
    public void shouldGetExchangeStepThreePageWhenThrowAccountsOperationException() {
        String expected = REDIRECT_TO_EXCHANGE_STEP_TWO_PAGE + "?currencyFromCode=" + CURRENCY_FROM_CODE +
                "&currencyToCode=" + CURRENCY_TO_CODE;
        when(accountFacade.getExchangeData(accountFrom, accountTo, SUM)).thenThrow(new AccountsOperationException(ERROR_MESSAGE));

        String result = testedInstance.getExchangeStepThreePage(ACCOUNT_FROM_CODE, ACCOUNT_TO_CODE, SUM, model, redirectAttributes, request);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(expected, result);
    }
}
