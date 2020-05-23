package com.alexbro.onlinebank.controller;

import com.alexbro.onlinebank.core.exception.LoginException;
import com.alexbro.onlinebank.facade.data.auth.AuthorizationRequest;
import com.alexbro.onlinebank.facade.data.auth.AuthData;
import com.alexbro.onlinebank.facade.auth.AuthFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPageControllerTest {

    private static final String USER_NAME = "alex123";
    private static final String USER_PASSWORD = "12345";
    private static final String LOGIN_PAGE = "pages/loginPage";
    private static final String ERROR_MESSAGE = "User is not found by this login and password!";

    @InjectMocks
    private LoginPageController testedEntry;

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
    private AuthorizationRequest authorizationRequest;

    @Before
    public void setUp(){
        when(request.getSession()).thenReturn(session);
        when(authorizationRequest.getUsername()).thenReturn(USER_NAME);
        when(authorizationRequest.getPassword()).thenReturn(USER_PASSWORD);
    }

    @Test
    public void shouldAuthorise(){
        when(authFacade.authorize(USER_NAME, USER_PASSWORD)).thenReturn(authData);

        testedEntry.authorize(authorizationRequest, request, model);

        verify(session).setAttribute("authData", authData);
    }

    @Test
    public void shouldReturnLoginPage(){
        when(authFacade.authorize(USER_NAME, USER_PASSWORD)).thenThrow( new LoginException(ERROR_MESSAGE));

        testedEntry.authorize(authorizationRequest, request, model);

        verify(model).addAttribute("error", ERROR_MESSAGE);

        assertEquals(LOGIN_PAGE, testedEntry.getLoginPage(model));
    }
}
