package com.alexbro.onlinebank.core.cronjob;

import com.alexbro.onlinebank.core.CoreConstants;
import com.alexbro.onlinebank.oer.facade.CurrencyUpdateFacade;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultCurrencyUpdateCronjob implements Cronjob {

    @Resource
    private CurrencyUpdateFacade currencyUpdateFacade;

    @Async
    @Override
    @Scheduled(cron = CoreConstants.CURRENCY_UPDATE_CRONJOB_TRIGGER)
    public void perform() {
        currencyUpdateFacade.update();
    }
}
