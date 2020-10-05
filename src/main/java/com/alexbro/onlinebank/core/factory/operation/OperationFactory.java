package com.alexbro.onlinebank.core.factory.operation;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Operation;

public interface OperationFactory {

    Operation createTransferOperation(Account accountForm, Account accountTo, Double sum);

    Operation createExchangeOperation(Account accountForm, Account accountTo, Double sum);
}
