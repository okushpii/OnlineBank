package com.alexbro.onlinebank.facade.data.factory;

import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultExchangeDataDataFactory implements ExchangeDataFactory {

    @Override
    public ExchangeData create(AccountData accountFrom, AccountData accountTo, BigDecimal sum, BigDecimal sumAfter,
                               BigDecimal balanceFrom, BigDecimal balanceTo, BigDecimal balanceAfterFrom, BigDecimal balanceAfterTo) {
        ExchangeData exchangeData = new ExchangeData();
        exchangeData.setAccountFrom(accountFrom);
        exchangeData.setAccountTo(accountTo);
        exchangeData.setSum(sum);
        exchangeData.setSumAfter(sumAfter);
        exchangeData.setBalanceFrom(balanceFrom);
        exchangeData.setBalanceTo(balanceTo);
        exchangeData.setBalanceAfterFrom(balanceAfterFrom);
        exchangeData.setBalanceAfterTo(balanceAfterTo);
        return exchangeData;
    }
}
