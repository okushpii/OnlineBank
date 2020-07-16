package com.alexbro.onlinebank.facade.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.core.service.validation.CurrencyValidationService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCurrencyFacadeTest {

    private static final String USER_CODE = "u1";
    private static final String CURRENCY_CODE = "c1";

    @InjectMocks
    private DefaultCurrencyFacade testedEntry;

    @Mock
    private CurrencyService currencyService;
    @Mock
    private Currency currency;
    @Mock
    private Converter<Currency, CurrencyData> currencyConverter;
    @Mock
    private CurrencyValidationService currencyValidationService;
    @Mock
    private List<Currency> currencyList;

    private List<CurrencyData> currencyDataList;

    @Before
    public void setUp() {
        currencyDataList = new ArrayList<>();
        when(currencyService.findAllByUser(USER_CODE)).thenReturn(currencyList);
        when(currencyConverter.convertAll(currencyList)).thenReturn(currencyDataList);
    }

    @Test
    public void shouldFindAllByUser() {
        List<CurrencyData> result = testedEntry.findAllByUser(USER_CODE);

        verify(currencyValidationService).validateCurrenciesSize(currencyList);
        assertEquals(currencyDataList, result);
    }

    @Test
    public void shouldFindByCode() {
        CurrencyData currencyData = new CurrencyData();
        when(currencyService.findByCode(CURRENCY_CODE)).thenReturn(Optional.of(currency));
        when(currencyConverter.convert(currency)).thenReturn(currencyData);

        Optional<CurrencyData> result = testedEntry.findByCode(CURRENCY_CODE);

        assertEquals(Optional.of(currencyData), result);
    }
}
