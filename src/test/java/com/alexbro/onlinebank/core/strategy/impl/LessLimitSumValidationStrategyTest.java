package com.alexbro.onlinebank.core.strategy.impl;

import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.config.ConfigurationService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LessLimitSumValidationStrategyTest {

    private static final BigDecimal SUM = BigDecimal.valueOf(1000);
    private static final String LIMIT = "20000";
    private static final BigDecimal INVALID_SUM = BigDecimal.valueOf(20000);
    private static final String ERROR_MESSAGE = "Error message";

    @InjectMocks
    private LessLimitSumValidationStrategy testedInstance;

    @Mock
    private ConfigurationService configurationService;
    @Mock
    private I18Service i18Service;

    @Before
    public void setUp() {
        when(configurationService.findRequired("transfer.limit")).thenReturn(LIMIT);
    }

    @Test
    public void shouldValidateSum() {
        testedInstance.validate(SUM);
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldValidateSumWhenSumIsBiggerThenLimit() {
        when(i18Service.getLocalizedValue("sum.is.bigger.then.limit.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.validate(INVALID_SUM);
    }
}
