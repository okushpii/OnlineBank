package com.alexbro.onlinebank.webfront.interceptor;

import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.webfront.interceptor.validator.PrincipalRoleValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminRoleInterceptorTest {

    @InjectMocks
    private AdminRoleInterceptor testedEntry;
    @Mock
    private PrincipalRoleValidator principalRoleValidator;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Object handler;

    @Test
    public void shouldPreHandleWhenTrue() throws Exception {
        when(principalRoleValidator.isRoleValid(request, Role.EMPLOYEE)).thenReturn(true);

        boolean result = testedEntry.preHandle(request, response, handler);

        assertTrue(result);
    }

    @Test
    public void shouldPreHandleWhenFalse() throws Exception {
        boolean result = testedEntry.preHandle(request, response, handler);

        assertFalse(result);
    }
}
