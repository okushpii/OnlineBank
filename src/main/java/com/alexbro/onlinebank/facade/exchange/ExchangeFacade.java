package com.alexbro.onlinebank.facade.exchange;

import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;

import java.math.BigDecimal;

public interface ExchangeFacade {

    ExchangeData getExchangeData(String accountCode, Long cardNumber, BigDecimal sum);
}
