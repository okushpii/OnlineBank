package com.alexbro.onlinebank.core.service.operation;

import com.alexbro.onlinebank.core.dao.operation.OperationDao;
import com.alexbro.onlinebank.core.entity.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOperationServiceTest {

    @InjectMocks
    private DefaultOperationService testedInstance;

    @Mock
    private OperationDao operationDao;

    @Mock
    private Operation operation;

    @Test
    public void shouldSave() {
        testedInstance.save(operation);

        verify(operationDao).save(operation);
    }
}
