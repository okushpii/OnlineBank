package com.alexbro.onlinebank.facade.data.transfer;

import com.alexbro.onlinebank.facade.validation.ValidationConstants;
import com.alexbro.onlinebank.facade.validation.costraint.BiggerZero;
import com.alexbro.onlinebank.facade.validation.costraint.LessLimit;

import javax.validation.constraints.Pattern;

public class TransferRequestData {

    private String accountCode;

    @Pattern(regexp = ValidationConstants.Regexp.CARD_NUMBER_REGEXP, message = ValidationConstants.Messages.VALID_CARD_NUMBER)
    private String cardNumber;

    @BiggerZero(message = ValidationConstants.Messages.VALID_SUM)
    @LessLimit(message = ValidationConstants.Messages.SUM_IS_BIGGER_THEN_LIMIT_MESSAGE)
    private Double sum;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
