package com.alexbro.onlinebank.facade.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.facade.exception.CurrencyException;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.facade.FacadeConstants;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultCurrencyFacade implements CurrencyFacade {

    @Resource
    private CurrencyService currencyService;

    @Resource
    private I18Service i18Service;

    @Resource
    private Converter<Currency, CurrencyData> currencyConverter;

    @Override
    public List<CurrencyData> findAllByUser(String userCode) {
        List<Currency> currencies = currencyService.findAllByUser(userCode);
        if (currencies.size() >= 2) {
            return currencyConverter.convertAll(currencies);
        } else throw new CurrencyException(i18Service.getLocalizedValue(FacadeConstants.INSUFFICIENT_CURRENCIES_MESSAGE));
    }

    @Override
    public Optional<CurrencyData> findByCode(String code) {
        return currencyService.findByCode(code).map(c -> currencyConverter.convert(c));
    }
}
