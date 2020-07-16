package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.currency.CurrencyFacade;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
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
import java.util.ArrayList;
import java.util.Optional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeStepOnePageControllerTest {

    private static final String USER_CODE = "u1";
    private static final String FIRST_STEP_OF_EXCHANGE_PAGE = "pages/exchangeStepOnePage";
    private static final String REDIRECT_TO_USER_PAGE = "redirect:/user/";
    private static final String ERROR_MESSAGE = "Error message";

    @InjectMocks
    private ExchangeStepOnePageController testedInstance;

    @Mock
    private UserFacade userFacade;
    @Mock
    private Model model;
    @Mock
    private CurrencyFacade currencyFacade;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private RedirectAttributes redirectAttributes;

    private AuthData authData;
    private UserData userData;
    private List<CurrencyData> currencies;

    @Before
    public void setUp() {
        authData = new AuthData();
        authData.setUserCode(USER_CODE);
        userData = new UserData();
        currencies = new ArrayList<>();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("authData")).thenReturn(authData);
        when(userFacade.findByCode(USER_CODE)).thenReturn(Optional.of(userData));
    }

    @Test
    public void shouldGetFirstStepOfExchangePageWhenAuthDataIsPresent() {
        String result = testedInstance.getExchangeStepOnePage(request, model, redirectAttributes);

        verify(model).addAttribute("currencies", currencies);

        assertEquals(FIRST_STEP_OF_EXCHANGE_PAGE, result);
    }

    @Test
    public void shouldGetFirstStepOfExchangePageWhenAuthDataIsAbsent() {
        when(session.getAttribute("authData")).thenReturn(null);

        testedInstance.getExchangeStepOnePage(request, model, redirectAttributes);

        verify(model, never()).addAttribute("currencies", currencies);
    }

    @Test
    public void shouldGetFirstStepOfExchangePageWhenThrowAccountsOperationException() {
        doThrow(new AccountsOperationException(ERROR_MESSAGE)).when(model).addAttribute("currencies", currencies);

        String result = testedInstance.getExchangeStepOnePage(request, model, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(REDIRECT_TO_USER_PAGE + USER_CODE, result);
    }
}
