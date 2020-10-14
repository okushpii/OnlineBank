package com.alexbro.onlinebank.facade.data.operation;

import com.alexbro.onlinebank.core.entity.Type;
import com.alexbro.onlinebank.facade.data.account.AccountData;
import com.alexbro.onlinebank.facade.data.user.UserData;

public class OperationData {

    private String code;

    private Type type;

    private Long cardNumberFrom;

    private Long cardNumberTo;

    private String currencyFromName;

    private String currencyToName;

    private Double sum;

    private AccountData account;

    private UserData user;

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

    public AccountData getAccount() {
        return account;
    }

    public void setAccount(AccountData account) {
        this.account = account;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
