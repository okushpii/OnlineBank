package com.alexbro.onlinebank.webfront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.exception.AccountsOperationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    private static final String AUTH_DATA_ATTRIBUTE = "authData";
    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 512333L;
    private static final int SUM = 100;
    private static final String USER_REDIRECT = "redirect:/user/";
    private static final String USER_CODE = "u1";
    private static final String ERROR_MESSAGE = "user.error.message";

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

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AUTH_DATA_ATTRIBUTE)).thenReturn(authData);
        when(authData.getUserCode()).thenReturn(USER_CODE);
    }

    @Test
    public void shouldTransfer() {
        String result = testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, BigDecimal.valueOf(SUM), request, redirectAttributes);

        verify(accountFacade).transfer(ACCOUNT_CODE, CARD_NUMBER, BigDecimal.valueOf(SUM));
        assertEquals(USER_REDIRECT + USER_CODE, result);
    }

    @Test
    public void shouldReturnError() {
        doThrow(new AccountsOperationException(ERROR_MESSAGE)).when(accountFacade).transfer(ACCOUNT_CODE, CARD_NUMBER,
                BigDecimal.valueOf(SUM));

        String result = testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER,
                BigDecimal.valueOf(SUM), request, redirectAttributes);

        verify(redirectAttributes).addFlashAttribute("error", ERROR_MESSAGE);
        assertEquals(USER_REDIRECT + USER_CODE, result);

    }
}