package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.dao.account.AccountDao;
import com.alexbro.onlinebank.core.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public Optional<Account> findByCode(String code) {
        return accountDao.findByCode(code);
    }

    @Override
    public Optional<Account> findByCardNumber(Long cardNumber) {
        return accountDao.findByCardNumber(cardNumber);
    }

    @Override
    public List<Account> findAllByCurrency(String currencyCode) {
        return accountDao.findAllByCurrency(currencyCode);
    }
}
