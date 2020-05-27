package com.alexbro.onlinebank.auth.front.controller;

import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import com.alexbro.onlinebank.webfront.controller.AuthController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {
    private static final String USER_NAME = "alex123";
    private static final String USER_PASSWORD = "12345";
    private static final String LOGIN_PAGE = "pages/loginPage";
    private static final String ERROR_MESSAGE = "User is not found by this login and password!";

    @InjectMocks
    private AuthController testedEntry;

    @Mock
    private AuthFacade authFacade;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private Model model;
    @Mock
    private AuthData authData;
    @Mock
    private AuthRequest authRequest;

    @Before
    public void setUp(){
        when(request.getSession()).thenReturn(session);
        when(authRequest.getUsername()).thenReturn(USER_NAME);
        when(authRequest.getPassword()).thenReturn(USER_PASSWORD);
    }

    @Test
    public void shouldAuthorise(){
        when(authFacade.authorize(USER_NAME, USER_PASSWORD)).thenReturn(authData);

        testedEntry.authorize(authRequest, request, model);

        verify(session).setAttribute("authData", authData);
    }

    @Test
    public void shouldReturnLoginPage(){
        when(authFacade.authorize(USER_NAME, USER_PASSWORD)).thenThrow( new AuthException(ERROR_MESSAGE));

        testedEntry.authorize(authRequest, request, model);

        verify(model).addAttribute("error", ERROR_MESSAGE);

        String result = testedEntry.authorize(authRequest, request, model);

        assertEquals(LOGIN_PAGE, result);
    }
}
