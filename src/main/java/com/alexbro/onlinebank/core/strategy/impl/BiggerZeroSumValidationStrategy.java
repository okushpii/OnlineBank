package com.alexbro.onlinebank.core.strategy.impl;

import com.alexbro.onlinebank.core.CoreConstants;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.strategy.SumValidationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class BiggerZeroSumValidationStrategy implements SumValidationStrategy {

    @Resource
    private I18Service i18Service;

    @Override
    public void validate(BigDecimal sum) {
        if (sum.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(CoreConstants.INVALID_SUM_MESSAGE));
        }
    }
}
