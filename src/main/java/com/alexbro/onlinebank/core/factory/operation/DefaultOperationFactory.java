package com.alexbro.onlinebank.core.factory.operation;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.core.entity.Type;
import com.alexbro.onlinebank.core.service.id.IdGenerationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultOperationFactory implements OperationFactory {

    @Resource
    private IdGenerationService idGenerationService;

    @Override
    public Operation createTransferOperation(Account accountForm, Account accountTo, Type type, Double sum) {
        Operation operation = new Operation();
        operation.setCode(idGenerationService.generate());
        operation.setType(type);
        operation.setCardNumberFrom(accountForm.getCardNumber());
        operation.setCardNumberTo(accountTo.getCardNumber());
        operation.setSum(sum);
        operation.setAccount(accountForm);
        return operation;
    }

    @Override
    public Operation createExchangeOperation(Account accountForm, Account accountTo, Type type, Double sum) {
        Operation operation = new Operation();
        operation.setCode(idGenerationService.generate());
        operation.setType(type);
        operation.setCardNumberFrom(accountForm.getCardNumber());
        operation.setCardNumberTo(accountTo.getCardNumber());
        operation.setCurrencyFromName(accountForm.getCurrency().getName());
        operation.setCurrencyToName(accountTo.getCurrency().getName());
        operation.setSum(sum);
        operation.setAccount(accountForm);
        return operation;
    }
}
