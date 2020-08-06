package com.alexbro.onlinebank.webfront.controller;

import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.facade.user.UserFacade;
import com.alexbro.onlinebank.facade.validation.PasswordConfirmationValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final String REGISTRATION_REDIRECT = "redirect:/registration";
    private static final String REGISTRATION_PAGE = "pages/registrationPage";

    @InjectMocks
    private UserController testedInstance;

    @Mock
    private UserFacade userFacade;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private PasswordConfirmationValidator passwordConfirmationValidator;

    @Mock
    private RegisterData registerData;

    @Test
    public void shouldRegister() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = testedInstance.register(registerData, bindingResult);

        verify(passwordConfirmationValidator).validate(registerData, bindingResult);
        verify(userFacade).register(registerData);
        assertEquals(REGISTRATION_REDIRECT, result);
    }

    @Test
    public void shouldRegisterWhenBindingResultHasError() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = testedInstance.register(registerData, bindingResult);

        verify(userFacade, never()).register(registerData);
        assertEquals(REGISTRATION_PAGE, result);
    }
}
