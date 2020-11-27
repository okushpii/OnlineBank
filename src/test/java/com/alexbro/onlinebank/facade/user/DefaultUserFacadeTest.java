package com.alexbro.onlinebank.facade.user;

import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.core.service.user.UserService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.register.RegisterData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserFacadeTest {

    private static final String USER_CODE = "u1";
    private static final String ACCOUNT_CODE = "a1";

    @InjectMocks
    private DefaultUserFacade testedEntry;
    @Mock
    private UserService userService;
    @Mock
    private Converter<User, UserData> userConverter;
    @Mock
    private User user;
    @Mock
    private UserData userData;
    @Mock
    private RegisterData registerData;
    @Mock
    private Converter<RegisterData, User> registerConverter;

    @Test
    public void shouldFindByCode() {
        UserData userData = new UserData();
        when(userService.findByCode(USER_CODE)).thenReturn(Optional.of(user));
        when(userConverter.convert(user)).thenReturn(userData);

        Optional<UserData> result = testedEntry.findByCode(USER_CODE);

        assertEquals(Optional.of(userData), result);
    }

    @Test
    public void shouldRegister() {
        when(registerConverter.convert(registerData)).thenReturn(user);

        testedEntry.register(registerData);

        verify(userService).register(user);
    }

    @Test
    public void shouldFindByAccount() {
        when(userService.findByAccount(ACCOUNT_CODE)).thenReturn(Optional.of(user));
        when(userConverter.convert(user)).thenReturn(userData);

        Optional<UserData> result = testedEntry.findByAccount(ACCOUNT_CODE);

        assertEquals(Optional.of(userData), result);
    }
}
