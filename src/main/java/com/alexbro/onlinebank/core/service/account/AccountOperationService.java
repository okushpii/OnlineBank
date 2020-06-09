package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.entity.Account;

import java.math.BigDecimal;

public interface AccountOperationService {

    void transfer(Account from, Account to, BigDecimal sum);
}
