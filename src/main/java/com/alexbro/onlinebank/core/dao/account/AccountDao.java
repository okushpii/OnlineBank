package com.alexbro.onlinebank.core.dao.account;

import com.alexbro.onlinebank.core.entity.Account;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> getByCode(String code);

    Optional<Account> getByCardNumber(Long cardNumber);

    void update(Account account);
}
