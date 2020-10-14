package com.alexbro.onlinebank.facade.populator.operation;

import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.core.entity.Type;
import com.alexbro.onlinebank.facade.data.operation.OperationData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperationPopulatorTest {

    private static final String CODE = "o1";
    private static final Long CARD_NUMBER_FROM = 232342L;
    private static final Long CARD_NUMBER_TO = 432423L;
    private static final String CURRENCY_FROM_NAME = "dollar";
    private static final String CURRENCY_TO_NAME = "euro";
    private static final Double SUM = 123.0;


    @InjectMocks
    private OperationPopulator testedInstance;

    @Mock
    private Operation operation;

    @Test
    public void shouldPopulate() {
        OperationData operationData = new OperationData();
        prepareFields();

        testedInstance.populate(operation, operationData);

        assertFields(operationData);
    }

    private void assertFields(OperationData operationData) {
        assertEquals(CODE, operationData.getCode());
        assertEquals(Type.TRANSFER_OUTCOME, operationData.getType());
        assertEquals(CARD_NUMBER_FROM, operationData.getCardNumberFrom());
        assertEquals(CARD_NUMBER_TO, operationData.getCardNumberTo());
        assertEquals(CURRENCY_FROM_NAME, operationData.getCurrencyFromName());
        assertEquals(CURRENCY_TO_NAME, operationData.getCurrencyToName());
        assertEquals(SUM, operationData.getSum());
    }

    private void prepareFields() {
        when(operation.getCode()).thenReturn(CODE);
        when(operation.getType()).thenReturn(Type.TRANSFER_OUTCOME);
        when(operation.getCardNumberFrom()).thenReturn(CARD_NUMBER_FROM);
        when(operation.getCardNumberTo()).thenReturn(CARD_NUMBER_TO);
        when(operation.getCurrencyFromName()).thenReturn(CURRENCY_FROM_NAME);
        when(operation.getCurrencyToName()).thenReturn(CURRENCY_TO_NAME);
        when(operation.getSum()).thenReturn(SUM);
    }
}
