package com.alexbro.onlinebank.core.service.currency;

import com.alexbro.onlinebank.core.dao.currency.CurrencyDao;
import com.alexbro.onlinebank.core.entity.Currency;
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
public class DefaultCurrencyServiceTest {

    private static final String USER_CODE = "u1";

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
}
