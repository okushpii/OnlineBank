package com.alexbro.onlinebank.facade.populator.register;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.core.service.encode.password.EncodePasswordService;
import com.alexbro.onlinebank.core.service.id.UUIDGenerationService;
import com.alexbro.onlinebank.facade.data.register.RegisterData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserReversePopulatorTest {

    private static final String CODE = "sdasd212132";
    private static final String NAME = "name";
    private static final String EMAIL = "name";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ENCODED_PASSWORD = "encodedPassword";

    @InjectMocks
    private UserReversePopulator testedInstance;

    @Mock
    private RegisterData registerData;

    @Mock
    private UUIDGenerationService uuidGenerationService;

    @Mock
    private EncodePasswordService encodePasswordService;

    @Test
    public void shouldPopulate() {
        User user = new User();
        prepareFields();

        testedInstance.populate(registerData, user);

        assertFields(user);
    }

    private void prepareFields() {
        when(uuidGenerationService.generate()).thenReturn(CODE);
        when(registerData.getName()).thenReturn(NAME);
        when(registerData.getEmail()).thenReturn(EMAIL);
        when(registerData.getUsername()).thenReturn(USERNAME);
        when(registerData.getPassword()).thenReturn(PASSWORD);
        when(encodePasswordService.encodePassword(PASSWORD)).thenReturn(ENCODED_PASSWORD);
    }

    private void assertFields(User user) {
        assertEquals(CODE, user.getCode());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(USERNAME, user.getUsername());
        assertEquals(ENCODED_PASSWORD, user.getPassword());
    }
}
