package com.alexbro.onlinebank.facade.data.factory;

import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultExchangeDataDataFactoryTest {

    private static BigDecimal SUM = BigDecimal.valueOf(100);
    private static BigDecimal SUM_AFTER = BigDecimal.valueOf(1000);
    private static BigDecimal CURRENT_RATE = BigDecimal.valueOf(1);
    private static BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(10);
    private static BigDecimal BALANCE_FROM = BigDecimal.valueOf(1000);
    private static BigDecimal BALANCE_TO = BigDecimal.valueOf(1500);
    private static BigDecimal BALANCE_AFTER_FROM = BigDecimal.valueOf(900);
    private static BigDecimal BALANCE_AFTER_TO = BigDecimal.valueOf(2500);

    @InjectMocks
    private DefaultExchangeDataDataFactory testedEntry;

    @Mock
    private ExchangeData exchangeData;

    private AccountData accountFrom;
    private AccountData accountTo;

    @Test
    public void create() {
        accountFrom = new AccountData();
        accountTo = new AccountData();
        prepareFields();

        assertFields(exchangeData);
    }

    private void prepareFields() {
        when(exchangeData.getAccountFrom()).thenReturn(accountFrom);
        when(exchangeData.getAccountTo()).thenReturn(accountTo);
        when(exchangeData.getSum()).thenReturn(SUM);
        when(exchangeData.getSumAfter()).thenReturn(SUM_AFTER);
        when(exchangeData.getBalanceFrom()).thenReturn(BALANCE_FROM);
        when(exchangeData.getBalanceTo()).thenReturn(BALANCE_TO);
        when(exchangeData.getBalanceAfterFrom()).thenReturn(BALANCE_AFTER_FROM);
        when(exchangeData.getBalanceAfterTo()).thenReturn(BALANCE_AFTER_TO);
    }

    private void assertFields(ExchangeData exchangeData) {
        assertEquals(accountFrom, exchangeData.getAccountFrom());
        assertEquals(accountTo, exchangeData.getAccountTo());
        assertEquals(SUM, exchangeData.getSum());
        assertEquals(SUM_AFTER, exchangeData.getSumAfter());
        assertEquals(BALANCE_FROM, exchangeData.getBalanceFrom());
        assertEquals(BALANCE_TO, exchangeData.getBalanceTo());
        assertEquals(BALANCE_AFTER_FROM, exchangeData.getBalanceAfterFrom());
        assertEquals(BALANCE_AFTER_TO, exchangeData.getBalanceAfterTo());
    }
}
