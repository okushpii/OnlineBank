package com.alexbro.onlinebank.facade.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DefaultCurrencyFacade implements CurrencyFacade {

    @Resource
    private CurrencyService currencyService;

    @Resource
    private Converter<Currency, CurrencyData> currencyConverter;

    @Override
    public List<CurrencyData> findAllByUser(String userCode) {
        return currencyConverter.convertAll(currencyService.findAllByUser(userCode));
    }
}
