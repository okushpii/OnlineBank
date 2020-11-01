package com.alexbro.onlinebank.auth.front.controller;

import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.auth.facade.AuthFacade;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.data.AuthRequest;
import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.webfront.controller.AuthController;
import com.alexbro.onlinebank.webfront.urlresolver.UrlResolver;
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
    private static final String LOGIN_PAGE = "pages/common/loginPage";
    private static final String ERROR_MESSAGE = "User is not found by this login and password!";
    private static final String AUTH_DATA = "authData";
    private static final String LOGIN_REDIRECT = "redirect:/login";

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
    @Mock
    private UrlResolver urlResolver;

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(session);
        when(authRequest.getUsername()).thenReturn(USER_NAME);
        when(authRequest.getPassword()).thenReturn(USER_PASSWORD);
        when(authData.getRole()).thenReturn(Role.USER);
        when(urlResolver.resolve(Role.USER)).thenReturn("/user");
    }

    @Test
    public void shouldAuthorise() {
        when(authFacade.authorize(USER_NAME, USER_PASSWORD)).thenReturn(authData);

        testedEntry.authorize(authRequest, request, model);

        verify(session).setAttribute(AUTH_DATA, authData);
    }

    @Test
    public void shouldReturnLoginPage() {
        when(authFacade.authorize(USER_NAME, USER_PASSWORD)).thenThrow(new AuthException(ERROR_MESSAGE));

        String result = testedEntry.authorize(authRequest, request, model);

        verify(model).addAttribute("error", ERROR_MESSAGE);
        assertEquals(LOGIN_PAGE, result);
    }

    @Test
    public void shouldLogOut() {
        AuthData authData = new AuthData();
        when(session.getAttribute("authData")).thenReturn(authData);

        String result = testedEntry.logout(request);

        verify(session).removeAttribute(AUTH_DATA);
        assertEquals(LOGIN_REDIRECT, result);
    }
}
