package com.alexbro.onlinebank.core.service.account;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DefaultAccountCalculationService implements AccountCalculationService {

    @Override
    public BigDecimal calculateSumAfterExchange(BigDecimal sum, BigDecimal currentRate, BigDecimal exchangeRate) {
        return sum.multiply(currentRate).divide(exchangeRate, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateBalanceWithDelta(BigDecimal money, BigDecimal delta) {
        return money.add(delta);
    }
}
