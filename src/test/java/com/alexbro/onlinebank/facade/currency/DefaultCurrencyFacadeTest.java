package com.alexbro.onlinebank.facade.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCurrencyFacadeTest {

    private static final String USER_CODE = "u1";

    @InjectMocks
    private DefaultCurrencyFacade testedEntry;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private Currency currency;

    @Mock
    private Converter<Currency, CurrencyData> currencyConverter;

    @Test
    public void shouldFindAllByUser() {
        List<CurrencyData> currencies = new ArrayList<>();
        when(currencyService.findAllByUser(USER_CODE)).thenReturn(List.of(currency));
        when(currencyConverter.convertAll(List.of(currency))).thenReturn(currencies);

        List<CurrencyData> result = testedEntry.findAllByUser(USER_CODE);

        assertEquals(currencies, result);
    }
}
