package com.alexbro.onlinebank.core.factory.operation;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.core.entity.Operation;
import com.alexbro.onlinebank.core.entity.Type;

public interface OperationFactory {

    Operation createTransferOperation(Account accountForm, Account accountTo, Type type, Double sum);

    Operation createExchangeOperation(Account accountForm, Account accountTo, Type type, Double sum);
}
