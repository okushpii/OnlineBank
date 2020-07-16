package com.alexbro.onlinebank.core.service.validation;

import com.alexbro.onlinebank.core.CoreConstants;
import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultCurrencyValidationService implements CurrencyValidationService {

    @Resource
    private I18Service i18Service;

    @Override
    public void validateCurrenciesSize(List<Currency> currencies) {
        if (currencies.size() < 2) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(CoreConstants.INSUFFICIENT_CURRENCIES_MESSAGE));
        }
    }

    @Override
    public void validateCurrenciesMatches(Currency currencyFrom, Currency currencyTo) {
        if (currencyFrom.equals(currencyTo)) {
            throw new AccountsOperationException(i18Service.getLocalizedValue(CoreConstants.CURRENCIES_MATCHES_EXCEPTION_MESSAGE));
        }
    }
}
