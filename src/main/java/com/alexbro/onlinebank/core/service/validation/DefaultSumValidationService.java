package com.alexbro.onlinebank.core.service.validation;

import com.alexbro.onlinebank.core.strategy.SumValidationStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultSumValidationService implements SumValidationService {

    @Resource
    private List<SumValidationStrategy> sumValidationStrategies;

    @Override
    public void validate(BigDecimal sum) {
        sumValidationStrategies.forEach(sv -> sv.validate(sum));
    }

    public void setSumValidationStrategies(List<SumValidationStrategy> sumValidationStrategies) {
        this.sumValidationStrategies = sumValidationStrategies;
    }
}
