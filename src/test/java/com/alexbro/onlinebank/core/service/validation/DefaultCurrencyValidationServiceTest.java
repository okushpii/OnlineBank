package com.alexbro.onlinebank.core.service.validation;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCurrencyValidationServiceTest {

    @InjectMocks
    private DefaultCurrencyValidationService testedInstance;

    @Mock
    private List<Currency> currencies;
    @Mock
    private Currency currencyFrom;
    @Mock
    private Currency currencyTo;
    @Mock
    private I18Service i18Service;

    @Test
    public void validateCurrenciesSize() {
        when(currencies.size()).thenReturn(2);

        testedInstance.validateCurrenciesSize(currencies);
    }

    @Test(expected = AccountsOperationException.class)
    public void validateCurrenciesSizeWhenSizeOfCurrenciesLessThenTwo() {
        when(currencies.size()).thenReturn(1);


        testedInstance.validateCurrenciesSize(currencies);
    }

    @Test
    public void validateCurrenciesMatches() {
        testedInstance.validateCurrenciesMatches(currencyFrom, currencyTo);
    }

    @Test(expected = AccountsOperationException.class)
    public void validateCurrenciesMatchesWhenCurrenciesMatches() {
        testedInstance.validateCurrenciesMatches(currencyFrom, currencyFrom);
    }
}
