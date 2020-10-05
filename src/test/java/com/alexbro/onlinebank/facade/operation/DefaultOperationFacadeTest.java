package com.alexbro.onlinebank.facade.operation;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.core.factory.operation.OperationFactory;
import com.alexbro.onlinebank.core.service.operation.OperationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOperationFacadeTest {

    private static Double SUM = 1000.0;

    @InjectMocks
    private DefaultOperationFacade testedInstance;

    @Mock
    private OperationService operationService;

    @Mock
    private OperationFactory operationFactory;

    @Mock
    private Operation operation;

    @Mock
    private Account accountFrom;

    @Mock
    private Account accountTo;


    @Test
    public void shouldSaveTransferOperation(){
        when(operationFactory.createTransferOperation(accountFrom, accountTo , SUM)).thenReturn(operation);

        testedInstance.saveTransferOperation(accountFrom, accountTo, SUM);

        verify(operationService).save(operation);
    }

    @Test
    public void shouldSaveExchangeOperation(){
        when(operationFactory.createExchangeOperation(accountFrom, accountTo , SUM)).thenReturn(operation);

        testedInstance.saveExchangeOperation(accountFrom, accountTo, SUM);

        verify(operationService).save(operation);
    }
}
