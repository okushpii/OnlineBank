package com.alexbro.onlinebank.oer.converter;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OerCurrencyConverterTest {

    @InjectMocks
    private OerCurrencyConverter testedInstance;

    @Mock
    private Map<String, Object> currencyMap;

    @Mock
    private Populator<Map<String, Object>, List<Currency>> oerCurrencyPopulator;

    @Test
    public void shouldConvert() {
        List<Currency> currencies = testedInstance.convert(currencyMap);

        verify(oerCurrencyPopulator).populate(currencyMap, currencies);
    }
}
