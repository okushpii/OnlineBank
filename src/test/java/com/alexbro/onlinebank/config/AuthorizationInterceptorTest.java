package com.alexbro.onlinebank.config;

import com.alexbro.onlinebank.facade.authentication.data.AuthenticationData;
import com.alexbro.onlinebank.facade.authentication.facade.AuthorizationFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationInterceptorTest {

    @InjectMocks
    private AuthorizationInterceptor testedEntry;

    @Mock
    private AuthorizationFacade authorizationFacade;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Object handler;

    @Mock
    private HttpSession session;

    @Mock
    private AuthenticationData authenticationData;

    @Before
    public void setUp(){
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("auth-data")).thenReturn(authenticationData);
    }

    @Test
    public void shouldPreHandleWhenUserIsAuthorised()throws Exception{
        when(authorizationFacade.isAuthorized(authenticationData)).thenReturn(true);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("auth-data")).thenReturn(authenticationData);

        assertTrue(testedEntry.preHandle(request, response, handler));
    }

    @Test
    public void shouldPreHandleWhenUserIsNotAuthorised()throws Exception{
        when(authorizationFacade.isAuthorized(authenticationData)).thenReturn(false);

        assertFalse(testedEntry.preHandle(request, response, handler));
    }
}
