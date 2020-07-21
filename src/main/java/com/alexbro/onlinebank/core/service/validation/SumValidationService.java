package com.alexbro.onlinebank.core.service.validation;

import java.math.BigDecimal;

public interface SumValidationService {

    void validate(BigDecimal sum);

    void validateAccountFromMoney(BigDecimal accountFromMoney, BigDecimal sum);
}
