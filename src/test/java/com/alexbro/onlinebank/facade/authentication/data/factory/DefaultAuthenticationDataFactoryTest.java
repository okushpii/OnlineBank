package com.alexbro.onlinebank.facade.authentication.data.factory;

import com.alexbro.onlinebank.core.entity.user.User;
import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAuthenticationDataFactoryTest {

    private static final String USER_NAME = "name";
    private static final String AUTHENTICATION_TOKEN = "21312eedw";

    @InjectMocks
    private DefaultAuthenticationDataFactory testedEntry;

    @Mock
    private AuthenticationData authenticationData;

    @Mock
    private User user;

    @Test
    public void shouldSetAuthorizationData() {
        prepareFields();
        testedEntry.createAuthenticationData(user, AUTHENTICATION_TOKEN);
        assertFields(authenticationData);
    }

    private void assertFields(AuthenticationData authenticationData) {
        assertEquals(USER_NAME, authenticationData.getUsername());
        assertEquals(AUTHENTICATION_TOKEN, authenticationData.getToken());
    }

    private void prepareFields() {
        when(authenticationData.getUsername()).thenReturn(USER_NAME);
        when(authenticationData.getToken()).thenReturn(AUTHENTICATION_TOKEN);
    }
}
