package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.currency.CurrencyFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeStepTwoPageControllerTest {

    private static final String USER_CODE = "u1";
    private static final String CURRENCY_FROM_CODE = "c1";
    private static final String CURRENCY_TO_CODE = "c2";
    private static final String EXCHANGE_STEP_TWO_PAGE = "pages/exchangeStepTwoPage";
    private static final String REDIRECT_TO_EXCHANGE_STEP_ONE_PAGE = "redirect:/exchangeStepOne";
    private static final String ERROR_MESSAGE = "Currencies matches";

    @InjectMocks
    private ExchangeStepTwoPageController testedInstance;

    @Mock
    private AccountFacade accountFacade;
    @Mock
    private UserFacade userFacade;
    @Mock
    private CurrencyFacade currencyFacade;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Mock
    private AuthManager authManager;
    @Mock
    private HttpSession session;
    @Mock
    private I18Service i18Service;

    @Mock
    private Model model;

    private UserData userData;
    private AuthData authData;
    private List<AccountData> accountsFrom;
    private List<AccountData> accountsTo;
    private CurrencyData currencyFrom;
    private CurrencyData currencyTo;

    @Before
    public void setUp() {
        authData = new AuthData();
        authData.setUserCode(USER_CODE);
        userData = new UserData();
        accountsFrom = new ArrayList<>();
        accountsTo = new ArrayList<>();
        currencyFrom = new CurrencyData();
        currencyTo = new CurrencyData();
        when(authManager.getAuthData(session)).thenReturn(authData);
        when(userFacade.findByCode(USER_CODE)).thenReturn(Optional.of(userData));
        when(accountFacade.findAllByCurrency(CURRENCY_FROM_CODE)).thenReturn(accountsFrom);
        when(accountFacade.findAllByCurrency(CURRENCY_TO_CODE)).thenReturn(accountsTo);
        when(currencyFacade.findByCode(CURRENCY_FROM_CODE)).thenReturn(Optional.of(currencyFrom));
        when(currencyFacade.findByCode(CURRENCY_TO_CODE)).thenReturn(Optional.of(currencyTo));
        when(i18Service.getLocalizedValue("currencies.matches")).thenReturn(ERROR_MESSAGE);
    }

    @Test
    public void shouldGetExchangeStepTwoPage() {
        String result = testedInstance.getExchangeStepTwoPage(CURRENCY_FROM_CODE, CURRENCY_TO_CODE, model, redirectAttributes, session);

        verify(model).addAttribute("accountsFrom", accountsFrom);
        verify(model).addAttribute("accountsTo", accountsTo);
        verify(model).addAttribute("currencyFrom", currencyFrom);
        verify(model).addAttribute("currencyTo", currencyTo);
        assertEquals(EXCHANGE_STEP_TWO_PAGE, result);
    }

    @Test
    public void shouldGetExchangeStepTwoPageWhenCurrenciesMatches() {
        String result = testedInstance.getExchangeStepTwoPage(CURRENCY_FROM_CODE, CURRENCY_FROM_CODE, model, redirectAttributes, session);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(REDIRECT_TO_EXCHANGE_STEP_ONE_PAGE, result);
    }
}
