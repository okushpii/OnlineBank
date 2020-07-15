package com.alexbro.onlinebank.facade.exchange;

import com.alexbro.onlinebank.core.service.account.AccountCalculationService;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.data.factory.ExchangeDataFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultExchangeFacadeTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 123456L;
    private static BigDecimal SUM = BigDecimal.valueOf(100);
    private static BigDecimal SUM_AFTER = BigDecimal.valueOf(1000);
    private static BigDecimal CURRENT_RATE = BigDecimal.valueOf(1);
    private static BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(10);
    private static BigDecimal BALANCE_FROM = BigDecimal.valueOf(1000);
    private static BigDecimal BALANCE_TO = BigDecimal.valueOf(1500);
    private static BigDecimal BALANCE_AFTER_FROM = BigDecimal.valueOf(900);
    private static BigDecimal BALANCE_AFTER_TO = BigDecimal.valueOf(2500);

    @InjectMocks
    private DefaultExchangeFacade testedEntry;

    @Mock
    private AccountFacade accountFacade;

    @Mock
    private AccountCalculationService accountCalculationService;

    @Mock
    private ExchangeDataFactory exchangeDataFactory;

    private AccountData accountFrom;
    private AccountData accountTo;

    @Test
    public void shouldGet() {
        ExchangeData exchangeData = new ExchangeData();
        setAccounts();
        when(accountFacade.findByCode(ACCOUNT_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountFacade.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(accountTo));
        when(accountCalculationService.calculateSumAfterExchange(SUM, CURRENT_RATE, EXCHANGE_RATE)).thenReturn(SUM_AFTER);
        when(accountCalculationService.calculateBalanceWithDelta(BALANCE_FROM, SUM.negate())).thenReturn(BALANCE_AFTER_FROM);
        when(accountCalculationService.calculateBalanceWithDelta(BALANCE_TO, SUM)).thenReturn(BALANCE_AFTER_TO);
        when(exchangeDataFactory.create(accountFrom, accountTo, SUM, SUM_AFTER, BALANCE_FROM, BALANCE_TO, BALANCE_AFTER_FROM, BALANCE_AFTER_TO)).
                thenReturn(exchangeData);

        ExchangeData result = testedEntry.getExchangeData(ACCOUNT_CODE, CARD_NUMBER, SUM);

        assertEquals(exchangeData, result);
    }

    private void setAccounts() {
        accountFrom = new AccountData();
        accountTo = new AccountData();
        accountFrom.setCurrency(new CurrencyData());
        accountTo.setCurrency(new CurrencyData());
        accountFrom.getCurrency().setRate(CURRENT_RATE);
        accountTo.getCurrency().setRate(EXCHANGE_RATE);
        accountFrom.setMoney(BALANCE_FROM);
        accountTo.setMoney(BALANCE_TO);
    }
}
