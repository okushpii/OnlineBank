package com.alexbro.onlinebank.facade.data.factory;

import com.alexbro.onlinebank.auth.facade.data.factory.DefaultAuthDataFactory;
import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.auth.facade.data.AuthData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAuthDataFactoryTest {

    private static final String USER_NAME = "name";
    private static final String AUTHENTICATION_TOKEN = "21312eedw";

    @InjectMocks
    private DefaultAuthDataFactory testedEntry;

    @Mock
    private AuthData authData;

    @Mock
    private User user;

    @Test
    public void shouldSetAuthorizationData() {
        prepareFields();

        testedEntry.create(user, AUTHENTICATION_TOKEN);

        assertFields(authData);
    }

    private void assertFields(AuthData authData) {
        assertEquals(USER_NAME, authData.getUsername());
        assertEquals(AUTHENTICATION_TOKEN, authData.getToken());
    }

    private void prepareFields() {
        when(authData.getUsername()).thenReturn(USER_NAME);
        when(authData.getToken()).thenReturn(AUTHENTICATION_TOKEN);
    }
}
