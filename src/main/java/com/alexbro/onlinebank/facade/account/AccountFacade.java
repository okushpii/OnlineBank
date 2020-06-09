package com.alexbro.onlinebank.facade.account;

import java.math.BigDecimal;

public interface AccountFacade {

    void transfer(String accountCode, Long cardNumber, BigDecimal sum);
}
