package com.alexbro.onlinebank.oer.populator;

import com.alexbro.onlinebank.core.entity.Currency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OerCurrencyPopulatorTest {

    private static final String CODE = "c1";
    private static final BigDecimal RATE = BigDecimal.valueOf(100.0);

    @InjectMocks
    private OerCurrencyPopulator testedInstance;

    private Map<String, Object> currencyMap;

    private Map<String, Object> rates;

    @Mock
    private Currency currency;

    @Before
    public void setUp(){
        currencyMap = new HashMap<>();
        rates = new HashMap<>();
        currencyMap.put("rates", rates);
        rates.put(CODE, RATE);
    }

    @Test
    public void shouldPopulate() {
        List<Currency> currencies = new ArrayList<>();

        testedInstance.populate(currencyMap, currencies);

        assertEquals(currencies.get(0).getRate(), RATE);
    }

}
