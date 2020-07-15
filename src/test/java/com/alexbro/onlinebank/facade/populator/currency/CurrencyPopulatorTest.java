package com.alexbro.onlinebank.facade.populator.currency;

import com.alexbro.onlinebank.core.entity.Currency;
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
public class CurrencyPopulatorTest {

    private static final String CURRENCY_CODE = "c1";
    private static final String CURRENCY_NAME = "dollar";
    private static BigDecimal CURRENCY_RATE = BigDecimal.valueOf(1.5);

    @InjectMocks
    private CurrencyPopulator testedInstance;

    @Mock
    private Currency currency;

    @Test
    public void shouldPopulate() {
        CurrencyData currencyData = new CurrencyData();
        prepareFields();

        testedInstance.populate(currency, currencyData);

        assertEquals(CURRENCY_CODE, currencyData.getCode());
        assertEquals(CURRENCY_NAME, currencyData.getName());
        assertEquals(CURRENCY_RATE, currencyData.getRate());
    }

    private void prepareFields() {
        when(currency.getCode()).thenReturn(CURRENCY_CODE);
        when(currency.getName()).thenReturn(CURRENCY_NAME);
        when(currency.getRate()).thenReturn(CURRENCY_RATE);
    }
}
