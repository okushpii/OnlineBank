package com.alexbro.onlinebank.core.strategy.impl;

import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BiggerZeroSumValidationStrategyTest {

    private static final int SUM = 1000;
    private static final int INVALID_SUM = -1000;
    private static final String ERROR_MESSAGE = "Error message";

    @InjectMocks
    private BiggerZeroSumValidationStrategy testedInstance;

    @Mock
    private I18Service i18Service;

    @Test
    public void validateSum() {
        testedInstance.validate(BigDecimal.valueOf(SUM));
    }

    @Test(expected = AccountsOperationException.class)
    public void validateSumWhenSumLessThenNull() {
        when(i18Service.getLocalizedValue("invalid.sum.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.validate(BigDecimal.valueOf(INVALID_SUM));
    }
}
