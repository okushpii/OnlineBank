package com.alexbro.onlinebank.webfront.controller.pages;

import com.alexbro.onlinebank.facade.account.AccountFacade;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeStepThreePageControllerTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 123456L;
    private static BigDecimal SUM = BigDecimal.valueOf(100);
    private static BigDecimal SUM_AFTER = BigDecimal.valueOf(1000);
    private static BigDecimal CURRENT_RATE = BigDecimal.valueOf(1);
    private static BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(10);
    private static BigDecimal BALANCE_FROM = BigDecimal.valueOf(1000);
    private static BigDecimal BALANCE_TO = BigDecimal.valueOf(1500);
    private static BigDecimal BALANCE_AFTER_FROM = BigDecimal.valueOf(900);
    private static BigDecimal BALANCE_AFTER_TO = BigDecimal.valueOf(2500);
    private static final String EXCHANGE_STEP_THREE_PAGE = "pages/exchangeStepThreePage";

    @InjectMocks
    private ExchangeStepThreePageController testedInstance;

    @Mock
    private AccountFacade accountFacade;

    @Mock
    private Model model;

    private AccountData accountFrom;
    private AccountData accountTo;

    @Test
    public void shouldGetExchangeStepThreePage() {
        setAccounts();
        when(accountFacade.findByCode(ACCOUNT_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountFacade.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(accountTo));
        when(accountFacade.calculateSumAfterExchange(SUM, CURRENT_RATE, EXCHANGE_RATE)).
                thenReturn(SUM_AFTER);
        when(accountFacade.calculateBalanceWithDelta(BALANCE_FROM, SUM.negate())).
                thenReturn(BALANCE_AFTER_FROM);
        when(accountFacade.calculateBalanceWithDelta(BALANCE_TO, SUM_AFTER)).
                thenReturn(BALANCE_AFTER_TO);

        String result = testedInstance.getExchangeStepThreePage(ACCOUNT_CODE, CARD_NUMBER, SUM, model);

        verify(model).addAttribute("accountFrom", accountFrom);
        verify(model).addAttribute("accountTo", accountTo);
        verify(model).addAttribute("sum", SUM);
        verify(model).addAttribute("sumAfter", SUM_AFTER);
        verify(model).addAttribute("balanceFrom", BALANCE_FROM);
        verify(model).addAttribute("balanceTo", BALANCE_TO);
        verify(model).addAttribute("balanceAfterFrom", BALANCE_AFTER_FROM);
        verify(model).addAttribute("balanceAfterTo", BALANCE_AFTER_TO);

        assertEquals(EXCHANGE_STEP_THREE_PAGE, result);
    }

    private void setAccounts() {
        accountFrom = new AccountData();
        accountTo = new AccountData();
        accountFrom.setCurrency(new CurrencyData());
        accountTo.setCurrency(new CurrencyData());
        accountFrom.getCurrency().setRate(CURRENT_RATE);
        accountTo.getCurrency().setRate(EXCHANGE_RATE);
        accountFrom.setMoney(BALANCE_FROM);
        accountTo.setMoney(BALANCE_TO);
    }
}
