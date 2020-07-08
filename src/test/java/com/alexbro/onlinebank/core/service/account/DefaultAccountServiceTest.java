package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.dao.account.AccountDao;
import com.alexbro.onlinebank.core.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAccountServiceTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 123345L;
    private static final String CURRENCY_CODE = "c1";

    @InjectMocks
    private DefaultAccountService testedEntry;

    @Mock
    private AccountDao accountDao;

    @Mock
    private Account account;

    @Before
    public void setUp() {
        when(accountDao.findByCode(ACCOUNT_CODE)).thenReturn(Optional.of(account));
        when(accountDao.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(account));
        when(accountDao.findAllByCurrency(CURRENCY_CODE)).thenReturn(List.of(account));
    }

    @Test
    public void shouldGetAccountByCode() {
        Optional<Account> result = testedEntry.findByCode(ACCOUNT_CODE);

        assertEquals(Optional.of(account), result);
    }

    @Test
    public void shouldGetAccountByCardNumber() {
        Optional<Account> result = testedEntry.findByCardNumber(CARD_NUMBER);

        assertEquals(Optional.of(account), result);
    }

    @Test
    public void shouldFindAllByCurrency() {
        List<Account> result = testedEntry.findAllByCurrency(CURRENCY_CODE);

        assertEquals(List.of(account), result);
    }
}
