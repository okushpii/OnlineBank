package com.alexbro.onlinebank.oer.converter;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Component
public class OerCurrencyConverter extends AbstractConverter<Map<String, Object>, List<Currency>> {

    @Resource
    private Populator<Map<String, Object>, List<Currency>> oerCurrencyPopulator;

    @Override
    public List<Currency> convert(Map<String, Object> currencyMap) {
        List<Currency> currencies = new ArrayList<>();
        oerCurrencyPopulator.populate(currencyMap, currencies);
        return currencies;
    }
}
