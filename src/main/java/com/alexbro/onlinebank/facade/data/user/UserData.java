package com.alexbro.onlinebank.facade.data.user;

import com.alexbro.onlinebank.facade.data.account.AccountData;

import java.util.List;

public class UserData {

    private String code;
    private String name;
    private String email;
    private List<AccountData> accounts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountData> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountData> accounts) {
        this.accounts = accounts;
    }
}
