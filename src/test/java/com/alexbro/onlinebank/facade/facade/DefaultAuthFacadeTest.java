package com.alexbro.onlinebank.facade.facade;

import com.alexbro.onlinebank.auth.exception.AuthException;
import com.alexbro.onlinebank.core.entity.Principal;
import com.alexbro.onlinebank.auth.model.service.AuthService;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.auth.model.service.AuthTokenService;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.auth.facade.data.factory.AuthDataFactory;
import com.alexbro.onlinebank.auth.facade.DefaultAuthFacade;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.service.principal.PrincipalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAuthFacadeTest {

    private static final String USER_NAME = "login";
    private static final String NOT_ENCODED_USER_PASSWORD = "12345";
    private static final String ENCODED_USER_PASSWORD = "oj23i1j23uij1";
    private static final String AUTHENTICATION_TOKEN = "21312eedw";
    private static final String INCORRECT_AUTHENTICATION_TOKEN = "dsad23231";
    private static final String ERROR_MESSAGE = "Account is not found";

    @InjectMocks
    private DefaultAuthFacade testedEntry;

    @Mock
    private PrincipalService principalService;
    @Mock
    private AuthService authService;
    @Mock
    private AuthTokenService authTokenService;
    @Mock
    private AuthDataFactory authDataFactory;
    @Mock
    private EncodePasswordService encodePasswordService;
    @Mock
    private Principal principal;
    @Mock
    private AuthData authData;
    @Mock
    private I18Service i18Service;

    @Before
    public void setUp() {
        when(principal.getPassword()).thenReturn(ENCODED_USER_PASSWORD);
        when(principal.getUsername()).thenReturn(USER_NAME);
        when(authData.getUsername()).thenReturn(USER_NAME);
        when(authData.getToken()).thenReturn(AUTHENTICATION_TOKEN);
        when(encodePasswordService.encodePassword(NOT_ENCODED_USER_PASSWORD)).thenReturn(ENCODED_USER_PASSWORD);
        when(authTokenService.generateToken(USER_NAME, ENCODED_USER_PASSWORD)).thenReturn(AUTHENTICATION_TOKEN);
        when(authDataFactory.create(principal, AUTHENTICATION_TOKEN)).thenReturn(authData);
        when(principalService.findByUsername(USER_NAME)).thenReturn(Optional.of(principal));
    }

    @Test
    public void shouldAuthoriseWhenUserIsPresent() {
        when(authService.passwordMatches(ENCODED_USER_PASSWORD, ENCODED_USER_PASSWORD)).thenReturn(true);

        AuthData result = testedEntry.authorize(USER_NAME, NOT_ENCODED_USER_PASSWORD);

        assertEquals(authData, result);
    }

    @Test(expected = AuthException.class)
    public void shouldAuthoriseWhenUserIsEmpty() {
        when(principalService.findByUsername(USER_NAME)).thenReturn(Optional.empty());

        testedEntry.authorize(USER_NAME, NOT_ENCODED_USER_PASSWORD);
    }

    @Test(expected = AuthException.class)
    public void shouldAuthoriseWhenPasswordsAreNotEquals() {
        testedEntry.authorize(USER_NAME, NOT_ENCODED_USER_PASSWORD);
    }

    @Test
    public void shouldIsAuthorisedWhenAuthenticationDataIsPresent() {
        boolean result = testedEntry.isAuthorized(authData);

        assertTrue(result);
    }

    @Test
    public void shouldIsAuthorisedWhenAuthenticationDataIsAbsent() {
        boolean result = testedEntry.isAuthorized(null);

        assertFalse(result);
    }

    @Test
    public void shouldIsAuthorisedWhenAuthenticationTokensAreNotEquals() {
        when(authData.getToken()).thenReturn(INCORRECT_AUTHENTICATION_TOKEN);

        boolean result = testedEntry.isAuthorized(authData);

        assertFalse(result);
    }

    @Test
    public void shouldIsCurrentUser() {
        testedEntry.isCurrentUser("u1", "u1");
    }

    @Test(expected = AuthException.class)
    public void shouldIsNotCurrentUser() {
        when(i18Service.getLocalizedValue("account.is.not.found.message")).thenReturn(ERROR_MESSAGE);

        testedEntry.isCurrentUser("u1", "u2");
    }
}
