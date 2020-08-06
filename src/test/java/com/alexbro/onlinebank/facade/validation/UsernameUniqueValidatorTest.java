package com.alexbro.onlinebank.facade.validation;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.core.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsernameUniqueValidatorTest {

    private static final String USERNAME = "username";

    @InjectMocks
    private UsernameUniqueValidator testedInstance;

    @Mock
    private ConstraintValidatorContext validatorContext;

    @Mock
    private UserService userService;

    @Mock
    private User user;

    @Test
    public void shouldIsValid() {
        when(userService.findByUsername(USERNAME)).thenReturn(Optional.empty());

        boolean result = testedInstance.isValid(USERNAME, validatorContext);

        assertTrue(result);
    }

    @Test
    public void shouldIsValidWhenUsernameIsNotUnique() {
        when(userService.findByUsername(USERNAME)).thenReturn(Optional.of(user));

        boolean result = testedInstance.isValid(USERNAME, validatorContext);

        assertFalse(result);
    }
}
