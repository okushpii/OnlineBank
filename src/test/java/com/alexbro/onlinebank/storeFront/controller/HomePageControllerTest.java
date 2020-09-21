package com.alexbro.onlinebank.storeFront.controller;

import com.alexbro.onlinebank.webfront.controller.pages.HomePageController;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
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
    private AuthManager authManager;

    @Mock
    private HttpSession session;

    private AuthData authData;

    private UserData userData;

    @Before
    public void setUp(){
        authData = new AuthData();
        authData.setUserCode(USER_CODE);
        userData = new UserData();
        when(authManager.getOptionalAuthData(session)).thenReturn(Optional.of(authData));
        when(userFacade.findByCode(USER_CODE)).thenReturn(Optional.of(userData));
    }

    @Test
    public void shouldAddUserAttribute() {
        String result = testedInstance.getHomePage(model, session);

        verify(model).addAttribute("user", userData);
        assertEquals(HOME_PAGE, result);
    }
}
