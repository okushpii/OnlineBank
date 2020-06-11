package com.alexbro.onlinebank.facade.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.service.account.AccountOperationService;
import com.alexbro.onlinebank.core.service.account.AccountService;
import com.alexbro.onlinebank.core.service.i18service.I18Service;
import com.alexbro.onlinebank.facade.exception.AccountsOperationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAccountFacadeTest {

    private static final String ACCOUNT_CODE = "a1";
    private static final Long CARD_NUMBER = 123345L;
    private static int SUM = 1000;
    private static String ERROR_MESSAGE = "Error message";

    @InjectMocks
    private DefaultAccountFacade testedInstance;

    @Mock
    private AccountService accountService;
    @Mock
    private AccountOperationService accountOperationService;
    @Mock
    private Account accountFrom;
    @Mock
    private Account accountTo;
    @Mock
    private I18Service i18Service;

    @Before
    public void setUp() {
        when(accountService.getByCode(ACCOUNT_CODE)).thenReturn(Optional.of(accountFrom));
        when(accountService.getByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(accountTo));
        when(accountFrom.getMoney()).thenReturn(BigDecimal.valueOf(5000));
    }

    @Test
    public void shouldTransfer() {
        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, BigDecimal.valueOf(SUM));

        verify(accountOperationService).transfer(accountFrom, accountTo, BigDecimal.valueOf(SUM));
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldTransferWhenAccountFromIsAbsent() {
        when(accountService.getByCode(ACCOUNT_CODE)).thenReturn(Optional.empty());
        when(i18Service.getLocalizedValue("account.is.not.found.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, BigDecimal.valueOf(SUM));
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldTransferWhenAccountToIsAbsent() {
        when(accountService.getByCardNumber(CARD_NUMBER)).thenReturn(Optional.empty());
        when(i18Service.getLocalizedValue("card.number.not.found.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, BigDecimal.valueOf(SUM));
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldTransferWhenSumIsInvalid() {
        when(i18Service.getLocalizedValue("invalid.sum.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, BigDecimal.valueOf(-1000));
    }

    @Test(expected = AccountsOperationException.class)
    public void shouldTransferWhenInsufficientFound() {
        when(accountFrom.getMoney()).thenReturn(BigDecimal.valueOf(500));
        when(i18Service.getLocalizedValue("accounts.operation.message")).thenReturn(ERROR_MESSAGE);

        testedInstance.transfer(ACCOUNT_CODE, CARD_NUMBER, BigDecimal.valueOf(SUM));
    }
}
