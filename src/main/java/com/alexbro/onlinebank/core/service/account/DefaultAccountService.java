package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.dao.account.AccountDao;
import com.alexbro.onlinebank.core.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public Optional<Account> getByCode(String code) {
        return accountDao.getByCode(code);
    }

    @Override
    public Optional<Account> getByCardNumber(Long cardNumber) {
        return accountDao.getByCardNumber(cardNumber);
    }
}
