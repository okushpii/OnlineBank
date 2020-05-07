package com.alexbro.onlinebank.core.user.service;

import com.alexbro.onlinebank.core.service.user.DefaultUserService;
import com.alexbro.onlinebank.core.dao.user.UserDao;
import com.alexbro.onlinebank.core.entity.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    private static final String USER_LOGIN = "login";

    @InjectMocks
    private DefaultUserService testedEntry;

    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    @Test
    public void shouldGetUserByLogin(){
        when(userDao.getUserByLogin(USER_LOGIN)).thenReturn(Optional.of(user));
        Optional<User> result = testedEntry.getUserByUsername(USER_LOGIN);

        assertEquals(Optional.of(user), result);
    }

}
