package com.alexbro.onlinebank.facade.operation;

import com.alexbro.onlinebank.core.entity.Account;

public interface OperationFacade {

    void saveTransferOperation(Account accountForm, Account accountTo, Double sum);

    void saveExchangeOperation(Account accountForm, Account accountTo, Double sum);
}
