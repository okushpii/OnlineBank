package com.alexbro.onlinebank.oer.facade;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.service.currency.CurrencyFetchingService;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCurrencyUpdateFacadeTest {

    @InjectMocks
    private DefaultCurrencyUpdateFacade testedInstance;

    @Mock
    private CurrencyFetchingService currencyFetchingService;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private Map<String, Object> currencyMap;

    @Mock
    private Converter<Map<String, Object>, List<Currency>> oerCurrencyConverter;

    @Test
    public void shouldUpdate() {
        List<Currency> currencies = new ArrayList<>();
        when(currencyFetchingService.fetch()).thenReturn(currencyMap);
        when(oerCurrencyConverter.convert(currencyMap)).thenReturn(currencies);

        testedInstance.update();

        verify(currencyService).save(currencies);
    }
}
