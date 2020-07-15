package com.alexbro.onlinebank.facade.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.exception.CurrencyException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCurrencyFacadeTest {

    private static final String USER_CODE = "u1";
    private static final String CURRENCY_CODE = "c1";
    private static final String ERROR_MESSAGE = "error";

    @InjectMocks
    private DefaultCurrencyFacade testedEntry;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private Currency currency1;

    @Mock
    private Currency currency2;

    @Mock
    private Converter<Currency, CurrencyData> currencyConverter;

    @Mock
    private I18Service i18Service;

    private List<Currency> currencyList;

    private List<CurrencyData> currencyDataList;

    @Before
    public void setUp(){
        currencyDataList = new ArrayList<>();
        currencyList = List.of(currency1, currency2);
        when(currencyService.findAllByUser(USER_CODE)).thenReturn(currencyList);
        when(currencyConverter.convertAll(currencyList)).thenReturn(currencyDataList);
        when(i18Service.getLocalizedValue("insufficient.currencies")).thenReturn(ERROR_MESSAGE);
    }

    @Test
    public void shouldFindAllByUser() {
        List<CurrencyData> result = testedEntry.findAllByUser(USER_CODE);

        assertEquals(currencyDataList, result);
    }

    @Test(expected = CurrencyException.class)
    public void shouldFindAllByUserWhenQuantityOfCurrenciesLessThen2(){
        currencyList = List.of(currency1);
        when(currencyService.findAllByUser(USER_CODE)).thenReturn(currencyList);

        testedEntry.findAllByUser(USER_CODE);
    }

    @Test
    public void shouldFindByCode() {
        CurrencyData currencyData = new CurrencyData();
        when(currencyService.findByCode(CURRENCY_CODE)).thenReturn(Optional.of(currency1));
        when(currencyConverter.convert(currency1)).thenReturn(currencyData);

        Optional<CurrencyData> result = testedEntry.findByCode(CURRENCY_CODE);

        assertEquals(Optional.of(currencyData), result);
    }
}
