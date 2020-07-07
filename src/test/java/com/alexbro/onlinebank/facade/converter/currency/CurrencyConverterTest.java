package com.alexbro.onlinebank.facade.converter.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterTest {

    @InjectMocks
    private CurrencyConverter testedInstance;

    @Mock
    private Populator<Currency, CurrencyData> currencyPopulator;

    @Mock
    private Currency currency;

    @Test
    public void shouldConvert(){
        CurrencyData currencyData = testedInstance.convert(currency);

        verify(currencyPopulator).populate(currency, currencyData);
    }
}
