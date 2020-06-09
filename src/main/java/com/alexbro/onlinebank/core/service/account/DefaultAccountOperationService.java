package com.alexbro.onlinebank.core.service.account;

import com.alexbro.onlinebank.core.dao.account.AccountDao;
import com.alexbro.onlinebank.core.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class DefaultAccountOperationService implements AccountOperationService {

    @Resource
    private AccountDao accountDao;

    @Override
    @Transactional
    public void transfer(Account from, Account to, BigDecimal sum) {
        from.setMoney(from.getMoney().subtract(sum));
        to.setMoney(to.getMoney().add(sum));
        accountDao.update(from);
        accountDao.update(to);
    }
}
