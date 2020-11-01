package com.alexbro.onlinebank.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends Principal {

    @Column
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
