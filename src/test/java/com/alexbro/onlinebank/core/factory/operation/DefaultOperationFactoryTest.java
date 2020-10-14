package com.alexbro.onlinebank.core.factory.operation;

import com.alexbro.onlinebank.core.entity.*;
import com.alexbro.onlinebank.core.service.id.IdGenerationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOperationFactoryTest {

    private static final String CODE = "22131dsmda";
    private static final Long CARD_NUMBER_FROM = 123345L;
    private static final Long CARD_NUMBER_TO = 232322L;
    private static final String CURRENCY_FROM_NAME = "euro";
    private static final String CURRENCY_TO_NAME = "dollar";
    private static Double SUM = 1000.0;

    @InjectMocks
    private DefaultOperationFactory testedInstance;

    @Mock
    private IdGenerationService idGenerationService;

    @Mock
    private Operation operation;

    @Mock
    private Account accountFrom;

    @Mock
    private Account accountTo;

    @Mock
    private Currency currencyFrom;

    @Mock
    private Currency currencyTo;

    @Before
    public void setUp() {
        when(accountFrom.getCardNumber()).thenReturn(CARD_NUMBER_FROM);
        when(accountTo.getCardNumber()).thenReturn(CARD_NUMBER_TO);
        when(accountFrom.getCurrency()).thenReturn(currencyFrom);
        when(accountTo.getCurrency()).thenReturn(currencyTo);
        when(currencyFrom.getName()).thenReturn(CURRENCY_FROM_NAME);
        when(currencyTo.getName()).thenReturn(CURRENCY_TO_NAME);
        when(idGenerationService.generate()).thenReturn(CODE);
        when(operation.getCode()).thenReturn(CODE);
        when(operation.getCardNumberFrom()).thenReturn(CARD_NUMBER_FROM);
        when(operation.getCardNumberTo()).thenReturn(CARD_NUMBER_TO);
        when(operation.getCurrencyFromName()).thenReturn(CURRENCY_FROM_NAME);
        when(operation.getCurrencyToName()).thenReturn(CURRENCY_TO_NAME);
        when(operation.getSum()).thenReturn(SUM);
    }

    @Test
    public void shouldCreateTransferOperation() {
        testedInstance.createTransferOperation(accountFrom, accountTo, Type.TRANSFER_OUTCOME, SUM);

        assertEquals(CODE, operation.getCode());
        assertEquals(CARD_NUMBER_FROM, operation.getCardNumberFrom());
        assertEquals(CARD_NUMBER_TO, operation.getCardNumberTo());
        assertEquals(SUM, operation.getSum());
    }

    @Test
    public void shouldCreateExchangeOperation() {
        testedInstance.createExchangeOperation(accountFrom, accountTo, Type.EXCHANGE_OUTCOME, SUM);

        assertEquals(CODE, operation.getCode());
        assertEquals(CARD_NUMBER_FROM, operation.getCardNumberFrom());
        assertEquals(CARD_NUMBER_TO, operation.getCardNumberTo());
        assertEquals(CURRENCY_FROM_NAME, operation.getCurrencyFromName());
        assertEquals(CURRENCY_TO_NAME, operation.getCurrencyToName());
        assertEquals(SUM, operation.getSum());
    }
}
