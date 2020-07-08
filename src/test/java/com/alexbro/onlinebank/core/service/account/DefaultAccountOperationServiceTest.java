package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.dao.account.AccountDao;
import com.alexbro.onlinebank.core.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAccountOperationServiceTest {

    private static final int ACCOUNT_FROM_MONEY = 5000;
    private static final int ACCOUNT_TO_MONEY = 1000;
    private static final int SUM = 500;

    @InjectMocks
    private DefaultAccountOperationService testedInstance;

    @Mock
    private Account accountFrom;
    @Mock
    private Account accountTo;
    @Mock
    private AccountDao accountDao;

    @Test
    public void shouldTransfer() {
        when(accountFrom.getMoney()).thenReturn(BigDecimal.valueOf(ACCOUNT_FROM_MONEY));
        when(accountTo.getMoney()).thenReturn(BigDecimal.valueOf(ACCOUNT_TO_MONEY));

        testedInstance.transfer(accountFrom, accountTo, BigDecimal.valueOf(SUM));

        verify(accountFrom).setMoney(BigDecimal.valueOf(4500));
        verify(accountTo).setMoney(BigDecimal.valueOf(1500));
        verify(accountDao).update(accountFrom);
        verify(accountDao).update(accountTo);
    }
}
