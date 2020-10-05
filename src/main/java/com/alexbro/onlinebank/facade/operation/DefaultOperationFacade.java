package com.alexbro.onlinebank.facade.operation;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.factory.operation.OperationFactory;
import com.alexbro.onlinebank.core.service.operation.OperationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultOperationFacade implements OperationFacade {

    @Resource
    private OperationService operationService;

    @Resource
    private OperationFactory operationFactory;

    @Override
    public void saveTransferOperation(Account accountForm, Account accountTo, Double sum) {
        operationService.save(operationFactory.createTransferOperation(accountForm, accountTo, sum));
    }

    @Override
    public void saveExchangeOperation(Account accountForm, Account accountTo, Double sum) {
        operationService.save(operationFactory.createExchangeOperation(accountForm, accountTo, sum));
    }
}
