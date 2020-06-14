package com.alexbro.onlinebank.facade.account;

import com.alexbro.onlinebank.core.service.config.ConfigurationService;
import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.service.account.AccountOperationService;
import com.alexbro.onlinebank.core.service.account.AccountService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.facade.FacadeConstants;
import com.alexbro.onlinebank.facade.exception.AccountsOperationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class DefaultAccountFacade implements AccountFacade {

    private static final String TRANSFER_LIMIT_KEY = "transfer.limit";

    @Resource
    private AccountService accountService;
    @Resource
    private AccountOperationService accountOperationService;
    @Resource
    private I18Service i18Service;
    @Resource
    private ConfigurationService configurationService;

    @Override
    public void transfer(String accountCode, Long cardNumber, BigDecimal sum) {

        validateSum(sum);
        validateSumLimit(sum);

        Account accountFrom = accountService.getByCode(accountCode).
                orElseThrow(() -> new AccountsOperationException(i18Service.getLocalizedValue(FacadeConstants.
                        ACCOUNT_IS_NOT_FOUND_MESSAGE)));

        validateAccountFromMoney(accountFrom.getMoney(), sum);

        Account accountTo = accountService.getByCardNumber(cardNumber).
                orElseThrow(() -> new AccountsOperationException(i18Service.
                        getLocalizedValue(FacadeConstants.CARD_NUMBER_NOT_FOUND_MESSAGE)));

        accountOperationService.transfer(accountFrom, accountTo, sum);
    }

    private void validateSum(BigDecimal sum) {
        if (sum.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(FacadeConstants.INVALID_SUM_MESSAGE));
        }
    }

    private void validateAccountFromMoney(BigDecimal accountFromMoney, BigDecimal sum) {
        if (accountFromMoney.subtract(sum).compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(FacadeConstants.ACCOUNTS_OPERATION_MESSAGE));
        }
    }

    private void validateSumLimit(BigDecimal sum) {
        BigDecimal limit = new BigDecimal(configurationService.findRequired(TRANSFER_LIMIT_KEY));
        if (sum.compareTo(limit) >= 0) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(FacadeConstants.SUM_IS_BIGGER_THEN_LIMIT_MESSAGE));
        }
    }
}
