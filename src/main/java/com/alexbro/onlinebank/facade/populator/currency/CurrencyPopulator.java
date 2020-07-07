package com.alexbro.onlinebank.facade.populator.currency;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;


@Component
public class CurrencyPopulator implements Populator<Currency, CurrencyData> {

    @Override
    public void populate(Currency currency, CurrencyData currencyData) {
        currencyData.setCode(currency.getCode());
        currencyData.setName(currency.getName());
        currencyData.setRate(currency.getRate());
    }
}
