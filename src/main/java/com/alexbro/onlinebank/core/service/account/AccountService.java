package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.entity.Account;

import java.util.Optional;
import java.util.List;

public interface AccountService {

    Optional<Account> findByCode(String code);

    Optional<Account> findByCardNumber(Long cardNumber);

    List<Account> findAllByCurrency(String currencyCode);
}
