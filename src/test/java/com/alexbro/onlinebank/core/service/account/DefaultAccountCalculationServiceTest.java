package com.alexbro.onlinebank.core.service.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAccountCalculationServiceTest {

    private static final BigDecimal DELTA = BigDecimal.valueOf(500);
    private static final BigDecimal CURRENT_RATE = BigDecimal.valueOf(1.2);
    private static final BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(0.1);
    private static final BigDecimal SUM_AFTER_EXCHANGE = BigDecimal.valueOf(6000.0);
    private static final BigDecimal BALANCE = BigDecimal.valueOf(4000);
    private static final BigDecimal BALANCE_AFTER_EXCHANGE = BigDecimal.valueOf(4500);

    @InjectMocks
    private DefaultAccountCalculationService testedEntry;

    @Test
    public void shouldCalculateSumAfterExchange() {
        BigDecimal result = testedEntry.calculateSumAfterExchange(DELTA, CURRENT_RATE, EXCHANGE_RATE);

        assertEquals(SUM_AFTER_EXCHANGE, result);
    }

    @Test
    public void shouldCalculateBalanceWithDelta() {
        BigDecimal result = testedEntry.calculateBalanceWithDelta(BALANCE, DELTA);

        assertEquals(BALANCE_AFTER_EXCHANGE, result);
    }
}
