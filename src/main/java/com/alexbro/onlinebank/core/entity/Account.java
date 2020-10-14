package com.alexbro.onlinebank.core.entity;

import com.alexbro.onlinebank.core.entity.common.IndexedEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "account")
public class Account extends IndexedEntity {

    @Column(nullable = false, unique = true)
    private Long cardNumber;

    @Column(nullable = false)
    private BigDecimal money = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Currency currency;

    @Column
    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
