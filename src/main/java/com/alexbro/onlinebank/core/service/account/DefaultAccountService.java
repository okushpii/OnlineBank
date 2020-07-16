package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.dao.account.AccountDao;
import com.alexbro.onlinebank.core.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
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

    @Override
    @Transactional
    public void transfer(Account from, Account to, BigDecimal sum) {
        from.setMoney(from.getMoney().subtract(sum));
        to.setMoney(to.getMoney().add(sum));
        accountDao.update(from);
        accountDao.update(to);
    }

    @Override
    @Transactional
    public void exchange(Account from, Account to, BigDecimal sumBeforeExchange, BigDecimal sumAfterExchange) {
        from.setMoney(from.getMoney().subtract(sumBeforeExchange));
        to.setMoney(to.getMoney().add(sumAfterExchange));
        accountDao.update(from);
        accountDao.update(to);
    }
}
