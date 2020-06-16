package com.alexbro.onlinebank.core.strategy;

import java.math.BigDecimal;

public interface SumValidationStrategy {

    void validate(BigDecimal sum);
}
