package com.alexbro.onlinebank.facade.data.factory;

import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;

import java.math.BigDecimal;

public interface ExchangeDataFactory {

    ExchangeData create(AccountData accountFrom, AccountData accountTo, BigDecimal sum, BigDecimal sumAfter,
                        BigDecimal balanceFrom, BigDecimal balanceTo, BigDecimal balanceAfterFrom, BigDecimal balanceAfterTo);
}
