package com.alexbro.onlinebank.facade.converter.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountConverterTest {

    @InjectMocks
    private AccountConverter testedInstance;

    @Mock
    private Populator<Account, AccountData> accountPopulator;

    @Mock
    private Account account;

    @Test
    public void shouldConvert(){
        AccountData accountData = testedInstance.convert(account);

        verify(accountPopulator).populate(account, accountData);
    }
}
