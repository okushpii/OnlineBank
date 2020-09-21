package com.alexbro.onlinebank.oer.populator;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.facade.populator.util.Populator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OerCurrencyPopulator implements Populator<Map<String, Object>, List<Currency>> {

    @Override
    public void populate(Map<String, Object> currencyMap, List<Currency> currencies) {
        Map<String, Object> rates = (Map<String, Object>) currencyMap.get("rates");
        currencies.addAll(rates.entrySet().stream()
                .map(e -> createCurrency(e.getKey(), e.getValue())).collect(Collectors.toList()));
    }

    private Currency createCurrency(String code, Object rate) {
        Currency currency = new Currency();
        currency.setCode(code);
        currency.setRate(BigDecimal.valueOf(Double.parseDouble(rate.toString())));
        return currency;
    }
}
