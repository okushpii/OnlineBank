package com.alexbro.onlinebank.facade.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.service.account.AccountService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.core.exception.AccountsOperationException;
import com.alexbro.onlinebank.core.service.validation.SumValidationService;
import com.alexbro.onlinebank.facade.converter.utill.Converter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
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

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 123345L;
    private static BigDecimal SUM = BigDecimal.valueOf(1000);
    private static String ERROR_MESSAGE = "Error message";
    private static final String CURRENCY_CODE = "c1";

    @InjectMocks
    private DefaultAccountFacade testedInstance;

    @Mock
    private AccountService accountService;
    @Mock
    private Account accountFrom;
    @Mock
    private Account accountTo;
    @Mock
    private Account account;
    @Mock
    private I18Service i18Service;
    @Mock
    private SumValidationService sumValidationService;
    @Mock
    private Converter<Account, AccountData> accountConverter;

    private AccountData accountData;

    @Before
    public void setUp() {
        accountData = new AccountData();
        when(accountService.findByCode(ACCOUNT_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountService.findByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(accountTo));
        when(accountFrom.getMoney()).thenReturn(BigDecimal.valueOf(5000));
    }

    @Test
    public void shouldTransfer() {
        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, SUM);

        verify(sumValidationService).validate(SUM);
        verify(accountService).transfer(accountFrom, accountTo, SUM);
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

    @Test(expected = AccountsOperationException.class)
    public void shouldTransferWhenInsufficientFound() {
        when(accountFrom.getMoney()).thenReturn(BigDecimal.valueOf(500));
        when(i18Service.getLocalizedValue("accounts.operation.message")).thenReturn(ERROR_MESSAGE);

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
}
