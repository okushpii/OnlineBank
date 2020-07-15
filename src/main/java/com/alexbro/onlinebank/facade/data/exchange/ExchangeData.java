package com.alexbro.onlinebank.facade.data.exchange;

import com.alexbro.onlinebank.facade.data.account.AccountData;

import java.math.BigDecimal;

public class ExchangeData {

    private AccountData accountFrom;
    private AccountData accountTo;
    private BigDecimal sum;
    private BigDecimal sumAfter;
    private BigDecimal balanceFrom;
    private BigDecimal balanceTo;
    private BigDecimal balanceAfterFrom;
    private BigDecimal balanceAfterTo;

    public AccountData getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(AccountData accountFrom) {
        this.accountFrom = accountFrom;
    }

    public AccountData getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(AccountData accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSumAfter() {
        return sumAfter;
    }

    public void setSumAfter(BigDecimal sumAfter) {
        this.sumAfter = sumAfter;
    }

    public BigDecimal getBalanceFrom() {
        return balanceFrom;
    }

    public void setBalanceFrom(BigDecimal balanceFrom) {
        this.balanceFrom = balanceFrom;
    }

    public BigDecimal getBalanceTo() {
        return balanceTo;
    }

    public void setBalanceTo(BigDecimal balanceTo) {
        this.balanceTo = balanceTo;
    }

    public BigDecimal getBalanceAfterFrom() {
        return balanceAfterFrom;
    }

    public void setBalanceAfterFrom(BigDecimal balanceAfterFrom) {
        this.balanceAfterFrom = balanceAfterFrom;
    }

    public BigDecimal getBalanceAfterTo() {
        return balanceAfterTo;
    }

    public void setBalanceAfterTo(BigDecimal balanceAfterTo) {
        this.balanceAfterTo = balanceAfterTo;
    }
}
