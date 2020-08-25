package com.alexbro.onlinebank.facade.validation;

import com.alexbro.onlinebank.core.service.config.ConfigurationService;
import com.alexbro.onlinebank.facade.validation.costraint.LessLimit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LessLimitValidator implements ConstraintValidator<LessLimit, Double> {

    private static final String TRANSFER_LIMIT_KEY = "transfer.limit";

    @Resource
    private ConfigurationService configurationService;

    @Override
    public boolean isValid(Double sum, ConstraintValidatorContext constraintValidatorContext) {
        Double limit = Double.valueOf(configurationService.findRequired(TRANSFER_LIMIT_KEY));
        if (sum != null) {
            return sum.compareTo(limit) <= 0;
        }return true;
    }
}
