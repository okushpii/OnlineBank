package com.alexbro.onlinebank.core.service.currency;

import com.alexbro.onlinebank.core.dao.currency.CurrencyDao;
import com.alexbro.onlinebank.core.entity.Currency;
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
public class DefaultCurrencyServiceTest {

    private static final String USER_CODE = "u1";
    private static final String CURRENCY_CODE = "c1";

    @InjectMocks
    private DefaultCurrencyService testedEntry;

    @Mock
    private CurrencyDao currencyDao;

    @Test
    public void shouldFindAllByUser() {
        List<Currency> currencies = new ArrayList<>();
        when(currencyDao.findAllByUser(USER_CODE)).thenReturn(currencies);

        List<Currency> result = testedEntry.findAllByUser(USER_CODE);

        assertEquals(currencies, result);
    }

    @Test
    public void shouldFindByCode() {
        Currency currency = new Currency();
        when(currencyDao.findByCode(CURRENCY_CODE)).thenReturn(Optional.of(currency));

        Optional<Currency> result = testedEntry.findByCode(CURRENCY_CODE);

        assertEquals(Optional.of(currency), result);
    }

    @Test
    public void shouldSave() {
        List<Currency> currencies = new ArrayList<>();

        testedEntry.save(currencies);

        verify(currencyDao).save(currencies);
    }
}
