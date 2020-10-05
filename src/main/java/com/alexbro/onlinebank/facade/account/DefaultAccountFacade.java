package com.alexbro.onlinebank.facade.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.service.account.AccountCalculationService;
import com.alexbro.onlinebank.core.service.account.AccountService;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.service.validation.CurrencyValidationService;
import com.alexbro.onlinebank.core.service.validation.SumValidationService;
import com.alexbro.onlinebank.facade.FacadeConstants;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.data.factory.ExchangeDataFactory;
import com.alexbro.onlinebank.facade.operation.OperationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultAccountFacade implements AccountFacade {

    @Resource
    private AccountService accountService;
    @Resource
    private I18Service i18Service;
    @Resource
    private CurrencyService currencyService;
    @Resource
    private AccountCalculationService accountCalculationService;
    @Resource
    private SumValidationService sumValidationService;
    @Resource
    private Converter<Account, AccountData> accountConverter;
    @Resource
    private ExchangeDataFactory exchangeDataFactory;
    @Resource
    private CurrencyValidationService currencyValidationService;
    @Resource
    private OperationFacade operationFacade;

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAccountFacade.class);

    @Override
    public void transfer(String accountCode, Long cardNumber, Double sum) {
        Account accountFrom = accountService.findByCode(accountCode).
                orElseThrow(() -> new AccountsOperationException(i18Service.getLocalizedValue(FacadeConstants.
                        ACCOUNT_IS_NOT_FOUND_MESSAGE)));

        sumValidationService.validateAccountFromMoney(accountFrom.getMoney(), BigDecimal.valueOf(sum));

        Account accountTo = accountService.findByCardNumber(cardNumber).
                orElseThrow(() -> new AccountsOperationException(i18Service.
                        getLocalizedValue(FacadeConstants.CARD_NUMBER_NOT_FOUND_MESSAGE)));

        LOG.info("Transfer start from card:" + accountFrom.getCardNumber() + " to card:" +
                accountTo.getCardNumber() + " Sum:" + sum);

        accountService.transfer(accountFrom, accountTo, BigDecimal.valueOf(sum));
        operationFacade.saveTransferOperation(accountFrom, accountTo, sum);
        LOG.info("Transaction is success");
    }

    @Override
    public List<AccountData> findAllByCurrency(String currencyCode) {
        return accountConverter.convertAll(accountService.findAllByCurrency(currencyCode));
    }

    @Override
    public Optional<AccountData> findByCode(String code) {
        return accountService.findByCode(code).map(a -> accountConverter.convert(a));
    }

    @Override
    public Optional<AccountData> findByCardNumber(Long cardNumber) {
        return accountService.findByCardNumber(cardNumber).map(a -> accountConverter.convert(a));
    }

    @Override
    public ExchangeData getExchangeData(AccountData accountFrom, AccountData accountTo, Double sum) {
        sumValidationService.validateAccountFromMoney(accountFrom.getMoney(), BigDecimal.valueOf(sum));

        BigDecimal sumAfter = accountCalculationService.calculateSumAfterExchange(BigDecimal.valueOf(sum), accountFrom.getCurrency().
                getRate(), accountTo.getCurrency().getRate());

        BigDecimal balanceAfterFrom = accountCalculationService.calculateBalanceWithDelta(accountFrom.getMoney(), BigDecimal.valueOf(sum).negate());

        BigDecimal balanceAfterTo = accountCalculationService.calculateBalanceWithDelta(accountTo.getMoney(), sumAfter);

        return exchangeDataFactory.create(accountFrom,
                accountTo,
                BigDecimal.valueOf(sum),
                sumAfter,
                accountFrom.getMoney(),
                accountTo.getMoney(),
                balanceAfterFrom, balanceAfterTo);
    }

    @Override
    public void exchange(String accountFromCode, String accountToCode, Double sum) {
        Account accountFrom = accountService.findByCode(accountFromCode).orElseThrow();
        sumValidationService.validateAccountFromMoney(accountFrom.getMoney(), BigDecimal.valueOf(sum));

        currencyValidationService.validateCurrenciesSize(currencyService.findAllByUser(accountFrom.getUser().getCode()));

        Account accountTo = accountService.findByCode(accountToCode).orElseThrow();

        currencyValidationService.validateCurrenciesMatches(accountFrom.getCurrency(), accountTo.getCurrency());

        BigDecimal sumAfterExchange = accountCalculationService.calculateSumAfterExchange(BigDecimal.valueOf(sum), accountFrom.
                getCurrency().getRate(), accountTo.getCurrency().getRate());

        LOG.info("Exchange start from currency:" + accountFrom.getCurrency().getName()
                + " to currency:" + accountTo.getCurrency().getName() + " From card:" + accountFrom.getCardNumber() + " to card:" +
                accountTo.getCardNumber());

        accountService.exchange(accountFrom, accountTo, BigDecimal.valueOf(sum), sumAfterExchange);
        operationFacade.saveExchangeOperation(accountFrom, accountTo, sum);
        LOG.info("Exchange is success");
    }
}
