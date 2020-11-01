package com.alexbro.onlinebank.facade.populator.user;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Role;
import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.user.UserData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPopulatorTest {

    private static final String USER_CODE = "u1";
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String PASSWORD = "password";

    @InjectMocks
    private UserPopulator testedInstance;

    @Mock
    private User user;

    @Mock
    private Account account;

    @Mock
    private AccountData accountData;

    @Mock
    private Converter<Account, AccountData>  accountsConverter;

    @Test
    public void shouldPopulate(){
        UserData userData = new UserData();
        prepareFields();

        testedInstance.populate(user, userData);

        assertEquals(USER_NAME, userData.getName());
        assertEquals(USER_EMAIL, userData.getEmail());
        assertEquals(PASSWORD, userData.getPassword());
        assertEquals(Role.USER, userData.getRole());
        assertEquals(List.of(accountData), userData.getAccounts());
    }

    private void prepareFields(){
        when(user.getCode()).thenReturn(USER_CODE);
        when(user.getName()).thenReturn(USER_NAME);
        when(user.getEmail()).thenReturn(USER_EMAIL);
        when(user.getPassword()).thenReturn(PASSWORD);
        when(user.getAccounts()).thenReturn(List.of(account));
        when(user.getRole()).thenReturn(Role.USER);
        when(accountsConverter.convertAll(List.of(account))).thenReturn(List.of(accountData));
    }
}
