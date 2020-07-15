package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
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
public class TransferPageControllerTest {

    private static final String USER_CODE = "u1";
    private static final String TRANSFER_PAGE = "pages/transferPage";

    @InjectMocks
    private TransferPageController testedInstance;

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
    public void setUp() {
        authData = new AuthData();
        authData.setUserCode(USER_CODE);
        userData = new UserData();
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("authData")).thenReturn(authData);
        when(userFacade.findByCode(USER_CODE)).thenReturn(Optional.of(userData));
    }

    @Test
    public void shouldAddUserAttributeWhenAuthDataIsPresent() {
        String result = testedInstance.getTransferPage(model, request);

        verify(model).addAttribute("accounts", userData.getAccounts());
        assertEquals(TRANSFER_PAGE, result);
    }

    @Test
    public void shouldAddUserAttributeWhenAuthDataIsAbsent() {
        when(session.getAttribute("authData")).thenReturn(null);

        testedInstance.getTransferPage(model, request);

        verify(model, never()).addAttribute("accounts", userData.getAccounts());
    }
}
