package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.exchange.ExchangeFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeStepThreePageControllerTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 123456L;
    private static BigDecimal SUM = BigDecimal.valueOf(100);
    private static final String EXCHANGE_STEP_THREE_PAGE = "pages/exchangeStepThreePage";

    @InjectMocks
    private ExchangeStepThreePageController testedInstance;

    @Mock
    private ExchangeFacade exchangeFacade;

    @Mock
    private Model model;

    @Test
    public void shouldGetExchangeStepThreePage() {
        ExchangeData exchangeData = new ExchangeData();
        when(exchangeFacade.getExchangeData(ACCOUNT_CODE, CARD_NUMBER, SUM)).thenReturn(exchangeData);

        String result = testedInstance.getExchangeStepThreePage(ACCOUNT_CODE, CARD_NUMBER, SUM, model);

        verify(model).addAttribute("exchange", exchangeData);
        assertEquals(EXCHANGE_STEP_THREE_PAGE, result);
    }
}
