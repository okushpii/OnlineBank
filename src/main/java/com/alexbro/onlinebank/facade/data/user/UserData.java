package com.alexbro.onlinebank.facade.data.user;

import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.principal.PrincipalData;

import java.util.List;

public class UserData extends PrincipalData {

    private List<AccountData> accounts;


    public List<AccountData> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountData> accounts) {
        this.accounts = accounts;
    }
}
