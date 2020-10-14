package com.alexbro.onlinebank.facade.converter.operation;

import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.facade.data.operation.OperationData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OperationConverterTest {

    @InjectMocks
    private OperationConverter testedEntry;

    @Mock
    private Operation operation;

    @Mock
    private Populator<Operation, OperationData> operationPopulator;

    @Test
    public void shouldConvert(){
        OperationData operationData = testedEntry.convert(operation);

        verify(operationPopulator).populate(operation, operationData);
    }
}
