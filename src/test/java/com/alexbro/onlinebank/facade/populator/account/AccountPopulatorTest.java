package com.alexbro.onlinebank.facade.populator.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AccountPopulatorTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 424223L;
    private static BigDecimal MONEY = BigDecimal.valueOf(10223);

    @InjectMocks
    private AccountPopulator testedInstance;

    @Mock
    private Converter<Currency, CurrencyData> currencyConverter;

    @Mock
    private Account account;

    @Mock
    private Currency currency;

    @Mock
    private CurrencyData currencyData;

    @Test
    public void shouldPopulate() {
        AccountData accountData = new AccountData();
        prepareFields();

        testedInstance.populate(account, accountData);

        assertEquals(ACCOUNT_CODE, accountData.getCode());
        assertEquals(CARD_NUMBER, accountData.getCardNumber());
        assertEquals(MONEY, accountData.getMoney());
        assertEquals(currencyData, accountData.getCurrency());
    }

    private void prepareFields(){
        when(account.getCode()).thenReturn(ACCOUNT_CODE);
        when(account.getCardNumber()).thenReturn(CARD_NUMBER);
        when(account.getMoney()).thenReturn(MONEY);
        when(account.getCurrency()).thenReturn(currency);
        when(currencyConverter.convert(currency)).thenReturn(currencyData);

    }
}
