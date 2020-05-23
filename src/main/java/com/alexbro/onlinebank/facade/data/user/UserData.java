package com.alexbro.onlinebank.facade.data.user;

import com.alexbro.onlinebank.core.entity.account.Account;

import java.util.List;

public class UserData {

    private String code;
    private String name;
    private String email;
    private List<Account> accounts;

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
