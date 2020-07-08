package com.alexbro.onlinebank.facade.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.service.account.AccountCalculationService;
import com.alexbro.onlinebank.core.service.account.AccountOperationService;
import com.alexbro.onlinebank.core.service.account.AccountService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.service.validation.SumValidationService;
import com.alexbro.onlinebank.facade.FacadeConstants;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
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
    private AccountOperationService accountOperationService;
    @Resource
    private AccountCalculationService accountCalculationService;
    @Resource
    private I18Service i18Service;
    @Resource
    private SumValidationService sumValidationService;
    @Resource
    private Converter<Account, AccountData> accountConverter;

    private static Logger logger = LoggerFactory.getLogger(DefaultAccountFacade.class);

    @Override
    public void transfer(String accountCode, Long cardNumber, BigDecimal sum) {
        sumValidationService.validate(sum);
        Account accountFrom = accountService.findByCode(accountCode).
                orElseThrow(() -> new AccountsOperationException(i18Service.getLocalizedValue(FacadeConstants.
                        ACCOUNT_IS_NOT_FOUND_MESSAGE)));

        validateAccountFromMoney(accountFrom.getMoney(), sum);

        Account accountTo = accountService.findByCardNumber(cardNumber).
                orElseThrow(() -> new AccountsOperationException(i18Service.
                        getLocalizedValue(FacadeConstants.CARD_NUMBER_NOT_FOUND_MESSAGE)));

        logger.info("Transfer start from card:" + accountFrom.getCardNumber() + " to card:" +
                accountTo.getCardNumber() + " Sum:" + sum);

        accountOperationService.transfer(accountFrom, accountTo, sum);
        logger.info("Transaction is success");
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
    public BigDecimal calculateSumAfterExchange(BigDecimal sum, BigDecimal currentRate, BigDecimal exchangeRate) {
        return accountCalculationService.calculateSumAfterExchange(sum, currentRate, exchangeRate);
    }

    @Override
    public BigDecimal calculateBalanceWithDelta(BigDecimal money, BigDecimal delta) {
        return accountCalculationService.calculateBalanceWithDelta(money, delta);
    }


    private void validateAccountFromMoney(BigDecimal accountFromMoney, BigDecimal sum) {
        if (accountFromMoney.subtract(sum).compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(FacadeConstants.ACCOUNTS_OPERATION_MESSAGE));
        }
    }
}
