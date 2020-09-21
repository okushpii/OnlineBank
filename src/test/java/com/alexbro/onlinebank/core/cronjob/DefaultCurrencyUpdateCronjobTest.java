package com.alexbro.onlinebank.core.cronjob;

import com.alexbro.onlinebank.oer.facade.CurrencyUpdateFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCurrencyUpdateCronjobTest {

    @InjectMocks
    private DefaultCurrencyUpdateCronjob testedInstance;

    @Mock
    private CurrencyUpdateFacade currencyUpdateFacade;

    @Test
    public void shouldUpdateCronjob() {
        testedInstance.perform();

        verify(currencyUpdateFacade).update();
    }
}
