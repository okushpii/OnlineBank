package com.alexbro.onlinebank.facade.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Currency;
import com.alexbro.onlinebank.core.entity.User;
import com.alexbro.onlinebank.core.service.account.AccountCalculationService;
import com.alexbro.onlinebank.core.service.account.AccountService;
import com.alexbro.onlinebank.core.service.currency.CurrencyService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.validation.CurrencyValidationService;
import com.alexbro.onlinebank.core.service.validation.SumValidationService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.exchange.ExchangeData;
import com.alexbro.onlinebank.facade.data.factory.ExchangeDataFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAccountFacadeTest {

    private static final String USER_CODE = "u1";
    private static final String ACCOUNT_CODE = "a1";
    private static final String ACCOUNT_TO_CODE = "a2";
    private static final Long CARD_NUMBER = 123345L;
    private static Double SUM = 1000.0;
    private static BigDecimal SUM_AFTER = BigDecimal.valueOf(1000);
    private static BigDecimal CURRENT_RATE = BigDecimal.valueOf(1);
    private static BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(10);
    private static BigDecimal BALANCE_FROM = BigDecimal.valueOf(1000);
    private static BigDecimal BALANCE_TO = BigDecimal.valueOf(1500);
    private static BigDecimal BALANCE_AFTER_FROM = BigDecimal.valueOf(900);
    private static BigDecimal BALANCE_AFTER_TO = BigDecimal.valueOf(2500);
    private static String ERROR_MESSAGE = "Error message";
    private static final String CURRENCY_CODE = "c1";

    @InjectMocks
    private DefaultAccountFacade testedInstance;

    @Mock
    private AccountService accountService;
    @Mock
    private AccountCalculationService accountCalculationService;
    @Mock
    private CurrencyService currencyService;
    @Mock
    private Account accountFrom;
    @Mock
    private Account accountTo;
    @Mock
    private Account account;
    @Mock
    private Currency currencyFrom;
    @Mock
    private Currency currencyTo;
    @Mock
    private List<Currency> currencies;
    @Mock
    private User user;
    @Mock
    private I18Service i18Service;
    @Mock
    private SumValidationService sumValidationService;
    @Mock
    private Converter<Account, AccountData> accountConverter;
    @Mock
    private ExchangeDataFactory exchangeDataFactory;
    @Mock
    private CurrencyValidationService currencyValidationService;

    private AccountData accountData;
    private AccountData accountFromData;
    private AccountData accountToData;

    @Before
    public void setUp() {
        accountData = new AccountData();
        setAccountsData();
        when(accountService.findByCode(ACCOUNT_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountService.findByCode(ACCOUNT_TO_CODE)).thenReturn(Optional.of(accountTo));
        when(accountService.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(accountTo));
        when(accountCalculationService.calculateSumAfterExchange(BigDecimal.valueOf(SUM), CURRENT_RATE, EXCHANGE_RATE)).thenReturn(SUM_AFTER);
        when(accountFrom.getMoney()).thenReturn(BALANCE_FROM);
        when(accountFrom.getUser()).thenReturn(user);
        when(user.getCode()).thenReturn(USER_CODE);
        when(currencyService.findAllByUser(USER_CODE)).thenReturn(currencies);
    }

    @Test
    public void shouldTransfer() {
        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, SUM);

        verify(sumValidationService).validateAccountFromMoney(BALANCE_FROM, BigDecimal.valueOf(SUM));
        verify(accountService).transfer(accountFrom, accountTo, BigDecimal.valueOf(SUM));
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldTransferWhenAccountFromIsAbsent() {
        when(accountService.findByCode(ACCOUNT_CODE)).thenReturn(Optional.empty());
        when(i18Service.getLocalizedValue("account.is.not.found.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, SUM);
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldTransferWhenAccountToIsAbsent() {
        when(accountService.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.empty());
        when(i18Service.getLocalizedValue("card.number.not.found.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, SUM);
    }

    @Test
    public void shouldFindAllByCurrency() {
        List<AccountData> accounts = new ArrayList<>();
        when(accountService.findAllByCurrency(CURRENCY_CODE)).thenReturn(List.of(account));
        when(accountConverter.convertAll(List.of(account))).thenReturn(accounts);

        List<AccountData> result = testedInstance.findAllByCurrency(CURRENCY_CODE);

        assertEquals(accounts, result);
    }

    @Test
    public void shouldFindByCode() {
        when(accountConverter.convert(accountFrom)).thenReturn(accountData);

        Optional<AccountData> result = testedInstance.findByCode(ACCOUNT_CODE);

        assertEquals(Optional.of(accountData), result);
    }

    @Test
    public void shouldFindByCardNumber() {
        when(accountConverter.convert(accountTo)).thenReturn(accountData);

        Optional<AccountData> result = testedInstance.findByCardNumber(CARD_NUMBER);

        assertEquals(Optional.of(accountData), result);
    }

    @Test
    public void shouldGetExchangeData() {
        ExchangeData exchangeData = new ExchangeData();
        when(accountCalculationService.calculateBalanceWithDelta(BALANCE_FROM, BigDecimal.valueOf(SUM).negate())).thenReturn(BALANCE_AFTER_FROM);
        when(accountCalculationService.calculateBalanceWithDelta(BALANCE_TO, SUM_AFTER)).thenReturn(BALANCE_AFTER_TO);
        when(exchangeDataFactory.create(accountFromData, accountToData, BigDecimal.valueOf(SUM), SUM_AFTER, BALANCE_FROM, BALANCE_TO,
                BALANCE_AFTER_FROM, BALANCE_AFTER_TO)).
                thenReturn(exchangeData);

        ExchangeData result = testedInstance.getExchangeData(accountFromData, accountToData, SUM);

        assertEquals(exchangeData, result);
    }

    @Test
    public void shouldExchange() {
        when(accountFrom.getCurrency()).thenReturn(currencyFrom);
        when(accountTo.getCurrency()).thenReturn(currencyTo);
        when(currencyFrom.getRate()).thenReturn(CURRENT_RATE);
        when(currencyTo.getRate()).thenReturn(EXCHANGE_RATE);

        testedInstance.exchange(ACCOUNT_CODE, ACCOUNT_TO_CODE, SUM);

        verify(currencyValidationService).validateCurrenciesSize(currencies);
        verify(currencyValidationService).validateCurrenciesMatches(currencyFrom, currencyTo);
        verify(sumValidationService).validateAccountFromMoney(BALANCE_FROM, BigDecimal.valueOf(SUM));
        verify(accountService).exchange(accountFrom, accountTo, BigDecimal.valueOf(SUM), SUM_AFTER);
    }

    private void setAccountsData() {
        accountFromData = new AccountData();
        accountToData = new AccountData();
        accountFromData.setCurrency(new CurrencyData());
        accountToData.setCurrency(new CurrencyData());
        accountFromData.getCurrency().setRate(CURRENT_RATE);
        accountToData.getCurrency().setRate(EXCHANGE_RATE);
        accountFromData.setMoney(BALANCE_FROM);
        accountToData.setMoney(BALANCE_TO);
    }
}
