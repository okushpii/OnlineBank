package com.alexbro.onlinebank.core.entity;

import com.alexbro.onlinebank.core.entity.common.IndexedEntity;

import javax.persistence.*;

@Entity
@Table(name = "operation")
public class Operation extends IndexedEntity {

    @Column
    private Type type;

    @Column
    private Long cardNumberFrom;

    @Column
    private Long cardNumberTo;

    @Column
    private String currencyFromName;

    @Column
    private String currencyToName;

    @Column
    private Double sum;

    @ManyToOne
    @JoinColumn
    private User user;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getCardNumberFrom() {
        return cardNumberFrom;
    }

    public void setCardNumberFrom(Long cardNumberFrom) {
        this.cardNumberFrom = cardNumberFrom;
    }

    public Long getCardNumberTo() {
        return cardNumberTo;
    }

    public void setCardNumberTo(Long cardNumberTo) {
        this.cardNumberTo = cardNumberTo;
    }

    public String getCurrencyFromName() {
        return currencyFromName;
    }

    public void setCurrencyFromName(String currencyFromName) {
        this.currencyFromName = currencyFromName;
    }

    public String getCurrencyToName() {
        return currencyToName;
    }

    public void setCurrencyToName(String currencyToName) {
        this.currencyToName = currencyToName;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
