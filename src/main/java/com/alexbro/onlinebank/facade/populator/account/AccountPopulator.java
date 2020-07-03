package com.alexbro.onlinebank.facade.populator.account;

import com.alexbro.onlinebank.core.entity.Account;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.populator.util.Populator;
import org.springframework.stereotype.Component;

@Component
public class AccountPopulator implements Populator<Account, AccountData> {

    @Override
    public void populate(Account account, AccountData accountData) {
        accountData.setCode(account.getCode());
        accountData.setCardNumber(account.getCardNumber());
        accountData.setMoney(account.getMoney());
    }
}
