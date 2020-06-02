package com.alexbro.onlinebank.core.entity;

import com.alexbro.onlinebank.core.entity.common.IndexedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account extends IndexedEntity {

    @Column(nullable = false, unique = true)
    private Long cardNumber;

    @Column(nullable = false)
    private BigDecimal money = BigDecimal.ZERO;

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
}
