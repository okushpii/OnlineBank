package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> getByCode(String code);

    Optional<Account> getByCardNumber(Long cardNumber);
}
