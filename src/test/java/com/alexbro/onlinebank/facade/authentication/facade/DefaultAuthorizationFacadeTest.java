package com.alexbro.onlinebank.facade.authentication.facade;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.core.service.authentication.AuthenticationService;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.core.service.user.UserService;
import com.alexbro.onlinebank.core.service.encode.token.AuthenticationTokenService;
import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;
import com.alexbro.onlinebank.facade.authentication.data.factory.AuthenticationDataFactory;
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
public class DefaultAuthorizationFacadeTest {

    private static final String USER_NAME = "login";
    private static final String NOT_ENCODED_USER_PASSWORD = "12345";
    private static final String ENCODED_USER_PASSWORD = "oj23i1j23uij1";
    private static final String AUTHENTICATION_TOKEN = "21312eedw";
    private static final String INCORRECT_AUTHENTICATION_TOKEN = "dsad23231";

    @InjectMocks
    private DefaultAuthorizationFacade testedEntry;

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private AuthenticationTokenService authenticationTokenService;
    @Mock
    private AuthenticationDataFactory authenticationDataFactory;
    @Mock
    private EncodePasswordService encodePasswordService;
    @Mock
    private User user;
    @Mock
    private AuthenticationData authenticationData;

    @Before
    public void setUp() {
        when(user.getPassword()).thenReturn(ENCODED_USER_PASSWORD);
        when(user.getUsername()).thenReturn(USER_NAME);
        when(authenticationData.getUsername()).thenReturn(USER_NAME);
        when(authenticationData.getToken()).thenReturn(AUTHENTICATION_TOKEN);
        when(encodePasswordService.encodePassword(NOT_ENCODED_USER_PASSWORD)).thenReturn(ENCODED_USER_PASSWORD);
        when(authenticationTokenService.generateToken(USER_NAME, ENCODED_USER_PASSWORD)).thenReturn(AUTHENTICATION_TOKEN);
        when(authenticationDataFactory.createAuthenticationData(user, AUTHENTICATION_TOKEN)).thenReturn(authenticationData);
        when(userService.getUserByUsername(USER_NAME)).thenReturn(Optional.of(user));
    }

    @Test
    public void shouldAuthoriseWhenUserIsPresent() {
        when(authenticationService.passwordMatches(ENCODED_USER_PASSWORD, ENCODED_USER_PASSWORD)).thenReturn(true);

        AuthenticationData result = testedEntry.authorize(USER_NAME, NOT_ENCODED_USER_PASSWORD);

        assertEquals(authenticationData, result);
    }

    @Test(expected = RuntimeException.class)
    public void shouldAuthoriseWhenUserIsEmpty() {
        when(userService.getUserByUsername(USER_NAME)).thenReturn(Optional.empty());

        testedEntry.authorize(USER_NAME, NOT_ENCODED_USER_PASSWORD);
    }

    @Test(expected = RuntimeException.class)
    public void shouldAuthoriseWhenPasswordsAreNotEquals() {
        testedEntry.authorize(USER_NAME, NOT_ENCODED_USER_PASSWORD);

    }

    @Test
    public void shouldIsAuthorisedWhenAuthenticationDataIsPresent() {
        assertTrue(testedEntry.isAuthorized(authenticationData));
    }

    @Test
    public void shouldIsAuthorisedWhenAuthenticationDataIsAbsent() {
        assertFalse(testedEntry.isAuthorized(null));
    }

    @Test
    public void shouldIsAuthorisedWhenAuthenticationTokensAreNotEquals() {
        when(authenticationData.getToken()).thenReturn(INCORRECT_AUTHENTICATION_TOKEN);
        assertFalse(testedEntry.isAuthorized(authenticationData));
    }

}
