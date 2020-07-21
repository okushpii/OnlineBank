package com.alexbro.onlinebank.core.service.validation;

import com.alexbro.onlinebank.core.CoreConstants;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.strategy.SumValidationStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultSumValidationService implements SumValidationService {

    @Resource
    private List<SumValidationStrategy> sumValidationStrategies;
    @Resource
    private I18Service i18Service;

    @Override
    public void validate(BigDecimal sum) {
        sumValidationStrategies.forEach(sv -> sv.validate(sum));
    }

    @Override
    public void validateAccountFromMoney(BigDecimal accountFromMoney, BigDecimal sum) {
        if (accountFromMoney.subtract(sum).compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(CoreConstants.ACCOUNTS_OPERATION_MESSAGE));
        }
    }

    public void setSumValidationStrategies(List<SumValidationStrategy> sumValidationStrategies) {
        this.sumValidationStrategies = sumValidationStrategies;
    }
}
