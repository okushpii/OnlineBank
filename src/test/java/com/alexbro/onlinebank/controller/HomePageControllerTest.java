package com.alexbro.onlinebank.controller;

import com.alexbro.onlinebank.facade.data.auth.AuthData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomePageControllerTest {

    private static final String USER_CODE = "u1";
    private static final String HOME_PAGE = "pages/homePage";

    @InjectMocks
    private HomePageController testedInstance;

    @Mock
    private UserFacade userFacade;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    private AuthData authData;

    private UserData userData;

    @Before
    public void setUp(){
        authData = new AuthData();
        authData.setUserCode(USER_CODE);
        userData = new UserData();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("authData")).thenReturn(authData);
        when(userFacade.getByCode(USER_CODE)).thenReturn(Optional.of(userData));
    }

    @Test
    public void shouldAddUserAttributeWhenAuthDataIsPresent() {
        testedInstance.getHomePage(model, request);

        verify(model).addAttribute("user", userData);
        assertEquals(testedInstance.getHomePage(model, request), HOME_PAGE);
    }

    @Test
    public void shouldAddUserAttributeWhenAuthDataIsAbsent(){
        when(session.getAttribute("authData")).thenReturn(null);

        testedInstance.getHomePage(model, request);

        verify(model, never()).addAttribute("user", userData);
    }
}
