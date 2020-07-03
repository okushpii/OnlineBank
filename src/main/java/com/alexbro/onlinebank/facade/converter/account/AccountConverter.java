package com.alexbro.onlinebank.facade.converter.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.facade.converter.utill.impl.AbstractConverter;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountConverter extends AbstractConverter<Account, AccountData> {

    @Resource
    private Populator<Account, AccountData> accountsPopulator;

    @Override
    public AccountData convert(Account account) {
        AccountData accountData = new AccountData();
        accountsPopulator.populate(account, accountData);
        return accountData;
    }
}
