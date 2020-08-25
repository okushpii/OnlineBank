package com.alexbro.onlinebank.facade.data.exchange;

import com.alexbro.onlinebank.facade.validation.ValidationConstants;
import com.alexbro.onlinebank.facade.validation.costraint.BiggerZero;
import com.alexbro.onlinebank.facade.validation.costraint.LessLimit;

public class ExchangeRequestData {

    private String accountFromCode;

    private String accountToCode;

    @BiggerZero(message = ValidationConstants.Messages.VALID_SUM)
    @LessLimit(message = ValidationConstants.Messages.SUM_IS_BIGGER_THEN_LIMIT_MESSAGE)
    private Double sum;

    public String getAccountFromCode() {
        return accountFromCode;
    }

    public void setAccountFromCode(String accountFromCode) {
        this.accountFromCode = accountFromCode;
    }

    public String getAccountToCode() {
        return accountToCode;
    }

    public void setAccountToCode(String accountToCode) {
        this.accountToCode = accountToCode;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
