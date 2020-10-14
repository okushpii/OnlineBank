package com.alexbro.onlinebank.facade.converter.operation;

import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.data.operation.OperationData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OperationConverter extends AbstractConverter<Operation, OperationData> {

    @Resource
    private Populator<Operation, OperationData> operationPopulator;

    @Override
    public OperationData convert(Operation operation) {
        OperationData operationData = new OperationData();
        operationPopulator.populate(operation, operationData);
        return operationData;
    }
}
