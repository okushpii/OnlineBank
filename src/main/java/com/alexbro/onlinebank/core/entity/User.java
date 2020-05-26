package com.alexbro.onlinebank.core.entity;

import com.alexbro.onlinebank.core.entity.common.IndexedEntity;
import com.alexbro.onlinebank.core.entity.Account;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends IndexedEntity {

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    @OneToMany
    private List<Account> accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmaill(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
