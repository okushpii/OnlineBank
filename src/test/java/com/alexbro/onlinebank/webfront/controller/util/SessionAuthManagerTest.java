package com.alexbro.onlinebank.webfront.controller.util;

import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionAuthManagerTest {

    private static final String  AUTH_DATA_ATTRIBUTE = "authData";

    @InjectMocks
    private SessionAuthManager testedEntry;

    @Mock
    private HttpSession session;

    @Test
    public void shouldGetAuthData(){
        AuthData authData = new AuthData();
        when(session.getAttribute(AUTH_DATA_ATTRIBUTE)).thenReturn(authData);

        AuthData result = testedEntry.getAuthData(session);

        assertEquals(authData, result);
    }

    @Test(expected = AuthException.class)
    public void shouldGetAuthDataWhenAuthDataIsAbsent(){
        when(session.getAttribute(AUTH_DATA_ATTRIBUTE)).thenReturn(null);

        testedEntry.getAuthData(session);
    }

}
