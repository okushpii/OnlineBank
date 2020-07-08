package com.alexbro.onlinebank.core.dao.account;

import com.alexbro.onlinebank.core.entity.Account;

import java.util.Optional;
import java.util.List;

public interface AccountDao {

    Optional<Account> findByCode(String code);

    Optional<Account> findByCardNumber(Long cardNumber);

    void update(Account account);

    List<Account> findAllByCurrency(String currencyCode);
}
