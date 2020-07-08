package com.alexbro.onlinebank.core.service.account;

import java.math.BigDecimal;

public interface AccountCalculationService {

    BigDecimal calculateSumAfterExchange(BigDecimal sum, BigDecimal currentRate, BigDecimal exchangeRate);

    BigDecimal calculateBalanceWithDelta(BigDecimal money, BigDecimal delta);
}
