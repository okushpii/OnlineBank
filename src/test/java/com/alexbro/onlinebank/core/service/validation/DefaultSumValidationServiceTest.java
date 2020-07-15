package com.alexbro.onlinebank.core.service.validation;

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

@RunWith(MockitoJUnitRunner.class)
public class DefaultSumValidationServiceTest {

    private static final BigDecimal SUM = BigDecimal.valueOf(1000);

    @InjectMocks
    private DefaultSumValidationService testedInstance;

    @Mock
    private BiggerZeroSumValidationStrategy firstStrategy;
    @Mock
    private LessLimitSumValidationStrategy secondStrategy;

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
}
