package com.alexbro.onlinebank.webfront.urlresolver;

import com.alexbro.onlinebank.core.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthUrlResolverTest {

    private static final String USER = "/user";

    @InjectMocks
    private AuthUrlResolver testedEntry;

    @Test
    public void shouldResolve() {
        String result = testedEntry.resolve(Role.USER);

        assertEquals(USER, result);
    }
}
