package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.dao.account.AccountDao;
import com.alexbro.onlinebank.core.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAccountServiceTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 123345L;
    private static final String CURRENCY_CODE = "c1";
    private static final BigDecimal SUM = BigDecimal.valueOf(500);
    private static final BigDecimal SUM_AFTER_EXCHANGE = BigDecimal.valueOf(1000);
    private static final BigDecimal ACCOUNT_FROM_MONEY = BigDecimal.valueOf(5000);
    private static final BigDecimal ACCOUNT_TO_MONEY = BigDecimal.valueOf(1000);

    @InjectMocks
    private DefaultAccountService testedEntry;

    @Mock
    private AccountDao accountDao;

    @Mock
    private Account account;

    @Mock
    private Account accountFrom;

    @Mock
    private Account accountTo;

    @Before
    public void setUp() {
        when(accountDao.findByCode(ACCOUNT_CODE)).thenReturn(Optional.of(account));
        when(accountDao.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(account));
        when(accountDao.findAllByCurrency(CURRENCY_CODE)).thenReturn(List.of(account));
        when(accountFrom.getMoney()).thenReturn(ACCOUNT_FROM_MONEY);
        when(accountTo.getMoney()).thenReturn(ACCOUNT_TO_MONEY);
    }

    @Test
    public void shouldFindByCode() {
        Optional<Account> result = testedEntry.findByCode(ACCOUNT_CODE);

        assertEquals(Optional.of(account), result);
    }

    @Test
    public void shouldFindByCardNumber() {
        Optional<Account> result = testedEntry.findByCardNumber(CARD_NUMBER);

        assertEquals(Optional.of(account), result);
    }

    @Test
    public void shouldFindAllByCurrency() {
        List<Account> result = testedEntry.findAllByCurrency(CURRENCY_CODE);

        assertEquals(List.of(account), result);
    }

    @Test
    public void shouldTransfer() {
        testedEntry.transfer(accountFrom, accountTo, SUM);

        verify(accountFrom).setMoney(BigDecimal.valueOf(4500));
        verify(accountTo).setMoney(BigDecimal.valueOf(1500));
        verify(accountDao).update(accountFrom);
        verify(accountDao).update(accountTo);
    }

    @Test
    public void shouldExchange() {
        testedEntry.exchange(accountFrom, accountTo, SUM, SUM_AFTER_EXCHANGE);

        verify(accountFrom).setMoney(BigDecimal.valueOf(4500));
        verify(accountTo).setMoney(BigDecimal.valueOf(2000));
        verify(accountDao).update(accountFrom);
        verify(accountDao).update(accountTo);
    }
}
