package com.alexbro.onlinebank.facade.validation;

import com.alexbro.onlinebank.facade.data.register.RegisterData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PasswordConfirmationValidatorTest {

    private static final String PASSWORD = "abcABC123";
    private static final String PASSWORD_CONFIRMATION = "abcABC123";
    private static final String WRONG_PASSWORD_CONFIRMATION = "123213sadasd";
    private static final String ERROR_MESSAGE = "password.not.matches";

    @InjectMocks
    private PasswordConfirmationValidator testedInstance;

    @Mock
    private RegisterData registerData;

    @Mock
    private Errors errors;

    @Before
    public void setUp(){
        when(registerData.getPassword()).thenReturn(PASSWORD);
    }

    @Test
    public void shouldValidate(){
        when(registerData.getPasswordConfirmation()).thenReturn(WRONG_PASSWORD_CONFIRMATION);

        testedInstance.validate(registerData, errors);

        verify(errors).rejectValue("passwordConfirmation", ERROR_MESSAGE);
    }

    @Test
    public void shouldValidateWhenPasswordsMatches(){
        when(registerData.getPasswordConfirmation()).thenReturn(PASSWORD_CONFIRMATION);

        testedInstance.validate(registerData, errors);

        verify(errors, never()).rejectValue("passwordConfirmation", ERROR_MESSAGE);
    }
}
