package com.alexbro.onlinebank.facade.data.account;

import com.alexbro.onlinebank.facade.data.currency.CurrencyData;
import com.alexbro.onlinebank.facade.data.operation.OperationData;

import java.math.BigDecimal;
import java.util.List;

public class AccountData {

    private String code;
    private Long cardNumber;
    private BigDecimal money;
    private CurrencyData currency;
    private List<OperationData> operations;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public CurrencyData getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyData currency) {
        this.currency = currency;
    }

    public List<OperationData> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationData> operations) {
        this.operations = operations;
    }
}
