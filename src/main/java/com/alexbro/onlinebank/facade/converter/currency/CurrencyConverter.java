package com.alexbro.onlinebank.facade.converter.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CurrencyConverter extends AbstractConverter<Currency, CurrencyData> {

    @Resource
    private Populator<Currency, CurrencyData> currencyPopulator;

    @Override
    public CurrencyData convert(Currency currency) {
        CurrencyData currencyData = new CurrencyData();
        currencyPopulator.populate(currency, currencyData);
        return currencyData;
    }
}
