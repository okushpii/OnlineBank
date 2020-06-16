package com.alexbro.onlinebank.core.strategy.impl;

import com.alexbro.onlinebank.core.CoreConstants;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.config.ConfigurationService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.strategy.SumValidationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class LessLimitSumValidationStrategy implements SumValidationStrategy {

    private static final String TRANSFER_LIMIT_KEY = "transfer.limit";

    @Resource
    private ConfigurationService configurationService;
    @Resource
    private I18Service i18Service;

    @Override
    public void validate(BigDecimal sum) {
        BigDecimal limit = new BigDecimal(configurationService.findRequired(TRANSFER_LIMIT_KEY));
        if (sum.compareTo(limit) >= 0) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(CoreConstants.SUM_IS_BIGGER_THEN_LIMIT_MESSAGE));
        }
    }
}
