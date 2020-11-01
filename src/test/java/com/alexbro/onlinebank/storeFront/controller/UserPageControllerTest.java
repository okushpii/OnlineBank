package com.alexbro.onlinebank.storeFront.controller;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.webfront.controller.pages.UserPageController;
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

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPageControllerTest {

    private static final String USER_CODE = "u1";
    private static final String USER_PAGE = "pages/user/userPage";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    @InjectMocks
    private UserPageController testedEntry;
    @Mock
    private UserFacade userFacade;
    @Mock
    private AuthManager authManager;
    @Mock
    private Model model;
    @Mock
    private HttpSession session;
    @Mock
    private AuthData authData;
    @Mock
    private UserData userData;

    @Before
    public void setUp() {
        when(authManager.getAuthData(session)).thenReturn(authData);
    }

    @Test
    public void shouldGetUserPage() {
        when(authData.getUserCode()).thenReturn(USER_CODE);
        when(userFacade.findByCode(USER_CODE)).thenReturn(Optional.of(userData));

        String result = testedEntry.getUserPage(USER_CODE, model, session);

        assertEquals(USER_PAGE, result);
    }

    @Test
    public void shouldGetLoginPage() {
        String result = testedEntry.getUserPage(USER_CODE, model, session);

        assertEquals(REDIRECT_LOGIN, result);
    }
}
