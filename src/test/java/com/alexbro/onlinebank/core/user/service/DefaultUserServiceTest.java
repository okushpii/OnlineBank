package com.alexbro.onlinebank.core.user.service;

import com.alexbro.onlinebank.core.service.user.DefaultUserService;
import com.alexbro.onlinebank.core.dao.user.UserDao;
import com.alexbro.onlinebank.core.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    private static final String USER_LOGIN = "login";
    private static final String USER_CODE = "u1";

    @InjectMocks
    private DefaultUserService testedEntry;

    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    @Test
    public void shouldFindByUserName() {
        when(userDao.findByUsername(USER_LOGIN)).thenReturn(Optional.of(user));

        Optional<User> result = testedEntry.findByUsername(USER_LOGIN);

        assertEquals(Optional.of(user), result);
    }

    @Test
    public void shouldFindByCode() {
        when(userDao.findByCode(USER_CODE)).thenReturn(Optional.of(user));

        Optional<User> result = testedEntry.findByCode(USER_CODE);

        assertEquals(Optional.of(user), result);
    }

    @Test
    public void shouldRegister() {
        User user = new User();

        testedEntry.register(user);

        verify(userDao).register(user);
    }
}
