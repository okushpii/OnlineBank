package com.alexbro.onlinebank.core.service.validation;

import com.alexbro.onlinebank.core.entity.Currency;

import java.util.List;

public interface CurrencyValidationService {

    void validateCurrenciesSize(List<Currency> currencies);

    void validateCurrenciesMatches(Currency currencyFrom, Currency currencyTo);
}
