package com.alexbro.onlinebank.facade.populator.operation;

import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.facade.data.operation.OperationData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

@Component
public class OperationPopulator implements Populator<Operation, OperationData> {

    @Override
    public void populate(Operation operation, OperationData operationData) {
        operationData.setCode(operation.getCode());
        operationData.setType(operation.getType());
        operationData.setCardNumberFrom(operation.getCardNumberFrom());
        operationData.setCardNumberTo(operation.getCardNumberTo());
        operationData.setCurrencyFromName(operation.getCurrencyFromName());
        operationData.setCurrencyToName(operation.getCurrencyToName());
        operationData.setSum(operation.getSum());
    }
}
