package com.alexbro.onlinebank.facade.exchange;

import com.alexbro.onlinebank.core.service.account.AccountCalculationService;
import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.data.factory.ExchangeDataFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class DefaultExchangeFacade implements ExchangeFacade {

    @Resource
    private ExchangeDataFactory exchangeDataFactory;
    @Resource
    private AccountFacade accountFacade;
    @Resource
    private AccountCalculationService accountCalculationService;

    @Override
    public ExchangeData getExchangeData(String accountCode, Long cardNumber, BigDecimal sum) {
        AccountData accountFrom = accountFacade.findByCode(accountCode).orElseThrow();
        AccountData accountTo = accountFacade.findByCardNumber(cardNumber).orElseThrow();
        BigDecimal sumAfter = accountCalculationService.calculateSumAfterExchange(sum, accountFrom.getCurrency().
                getRate(), accountTo.getCurrency().getRate());
        BigDecimal balanceAfterFrom = accountCalculationService.calculateBalanceWithDelta(accountFrom.getMoney(), sum.negate());
        BigDecimal balanceAfterTo = accountCalculationService.calculateBalanceWithDelta(accountTo.getMoney(), sum);

        return exchangeDataFactory.create(accountFrom,
                accountTo,
                sum,
                sumAfter,
                accountFrom.getMoney(),
                accountTo.getMoney(),
                balanceAfterFrom, balanceAfterTo);
    }
}
