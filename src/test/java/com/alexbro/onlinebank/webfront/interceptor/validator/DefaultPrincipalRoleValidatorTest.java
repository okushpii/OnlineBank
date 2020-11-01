package com.alexbro.onlinebank.webfront.interceptor.validator;

import com.alexbro.onlinebank.auth.facade.data.AuthData;
import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.webfront.controller.util.AuthManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPrincipalRoleValidatorTest {

    @InjectMocks
    private DefaultPrincipalRoleValidator testedEntry;
    @Mock
    private AuthData authData;
    @Mock
    private AuthManager authManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    private Role role;

    @Before
    public void setUp() {
        role = Role.EMPLOYEE;
        when(request.getSession()).thenReturn(session);
        when(authManager.getAuthData(session)).thenReturn(authData);
        when(authData.getRole()).thenReturn(role);
    }

    @Test
    public void shouldIsRoleValidWhenTrue() {
        boolean result = testedEntry.isRoleValid(request, role);

        assertTrue(result);
    }

    @Test
    public void shouldIsRoleValidWhenFalse() {
        boolean result = testedEntry.isRoleValid(request, Role.USER);

        assertFalse(result);
    }
}
