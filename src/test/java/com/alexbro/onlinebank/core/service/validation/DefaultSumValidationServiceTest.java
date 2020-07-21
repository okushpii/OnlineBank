package com.alexbro.onlinebank.core.service.validation;

import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.strategy.impl.BiggerZeroSumValidationStrategy;
import com.alexbro.onlinebank.core.strategy.impl.LessLimitSumValidationStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSumValidationServiceTest {

    private static final BigDecimal SUM = BigDecimal.valueOf(1000);
    private static final BigDecimal ACCOUNT_FROM_MONEY = BigDecimal.valueOf(4000);
    private static final BigDecimal INVALID_SUM = BigDecimal.valueOf(5000);
    private static final String ERROR_MESSAGE = "Error message";

    @InjectMocks
    private DefaultSumValidationService testedInstance;

    @Mock
    private BiggerZeroSumValidationStrategy firstStrategy;
    @Mock
    private LessLimitSumValidationStrategy secondStrategy;
    @Mock
    private I18Service i18Service;

    @Before
    public void setUp() {
        testedInstance.setSumValidationStrategies(List.of(firstStrategy, secondStrategy));
    }

    @Test
    public void shouldValidateSum() {
        testedInstance.validate(SUM);

        verify(firstStrategy).validate(SUM);
        verify(secondStrategy).validate(SUM);
    }

    @Test
    public void shouldValidateAccountFromMoney(){
        testedInstance.validateAccountFromMoney(ACCOUNT_FROM_MONEY, SUM);
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldValidateAccountFromMoneyWhenSumBiggerThenAccountFromMoney(){
        when(i18Service.getLocalizedValue("accounts.operation.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.validateAccountFromMoney(ACCOUNT_FROM_MONEY, INVALID_SUM);
    }
}
