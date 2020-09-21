package com.alexbro.onlinebank.oer.facade;

import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.core.service.currency.CurrencyFetchingService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.oer.OerConstants;
import com.alexbro.onlinebank.webfront.controller.pages.ExchangeStepTwoPageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Component
public class DefaultCurrencyUpdateFacade implements CurrencyUpdateFacade {

    @Resource
    private CurrencyFetchingService currencyFetchingService;

    @Resource
    private CurrencyService currencyService;

    @Resource
    private Converter<Map<String, Object>, List<Currency>> oerCurrencyConverter;

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeStepTwoPageController.class);

    @Override
    @Transactional
    public void update() {
        List<Currency> currencies = oerCurrencyConverter.convert(currencyFetchingService.fetch());
        currencyService.save(currencies);
        LOG.info(OerConstants.CURRENCIES_UPDATE_MESSAGE);
    }
}
